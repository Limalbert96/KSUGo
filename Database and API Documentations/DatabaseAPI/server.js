// server.js
// The reason we use the MD5 module is so, eventually, we can retrieve and store hashed passwords. Plaintext storage is a no-no.
// For demonstration purposes, however, we will keep our passwords in plaintext.
var express = require("express");
var mysql = require("mysql");
var bodyParser = require("body-parser");
var md5 = require('MD5');
var rest = require("./REST.js");
var app = express();
var port = 3000;
var pool = mysql.createPool({
		connectionLimit : 50,
		waitForConnection: true,
		host : 'localhost',
		user: 'root',
		password : 'spksugoadmin18',
		database: 'ksugo',
		insecureAuth: true,
		debug: false});

function REST(){
	var self = this;
	self.connectMysql();
	self.refreshConnection();
};

REST.prototype.connectMysql = function() {
	var self = this;
	pool.getConnection(function(err, connection){
		if(err){
			self.stop(err);
		}
		else {
			self.configureExpress(pool);
		}
	});
}
	REST.prototype.configureExpress = function(connection) {
		var self = this;
		app.use(bodyParser.urlencoded({ extended: true}));
		app.use(bodyParser.json());
		var router = express.Router();
		app.use('/api', router);
		var rest_router = new rest(router, connection, md5);
		self.startServer();
	}
	
	REST.prototype.startServer = function() {
		app.listen(port, function(){
			console.log("Server started successfully.");
			console.log("Listening on port " + port + ".");
		});
	}
	
	REST.prototype.stop = function(err) {
		console.log("ISSUE WITH MYSQL n" +err);
		process.exit(1);
	}
	
	REST.prototype.refreshConnection = function() {
		setInterval(function() {
			pool.query('SELECT 1');
		}, 5000);
	}
	new REST();

	
		