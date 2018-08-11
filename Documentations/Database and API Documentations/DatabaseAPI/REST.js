var mysql = require("mysql");
function REST_ROUTER(router,connection,md5) {
    var self = this;
    self.handleRoutes(router,connection,md5);
}
//Still need to be done:
//Database:
//API: post announcement.




REST_ROUTER.prototype.handleRoutes= function(router,connection,md5) {
	
	
	/*This retrieves all users in the database. Debugging Only, remove from official product.
	Format get request like so:
	URL/api/users
	Example: http://localhost:3000/api/users
	*/
    router.get("/users", function(req, res){
		var query = "SELECT * FROM users";
		var table = ["users"];
		query = mysql.format(query, table);
		connection.query(query, function(err, rows){
			if(err) {
				res.json({"Error" : true, "Message" : "Error executing MYSQL query"});
			} else {
				res.json({"Users" : rows});
			}
		});
	});
	
	/*This retrieves a specific user. 
	Format get request like so:
	URL/api/users/KSU_ID&PASSWORD
	Example: http://localhost:3000/api/users/nwilso54&1234
	*/
	router.get("/users/:ksu_id&:password",function(req,res){
		var query = "SELECT `ksu id`, `is student`, `first name`, `last name` FROM users WHERE `ksu id`=? AND password=?";
		var table = [req.params.ksu_id, req.params.password];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Users" : rows});
			}
		});
	});
	/*Retrieves all faculty and orders them by last name*/
		router.get("/users/faculty",function(req,res){
		var query = "select * from faculty order by `last name`;";
		var table = ["faculty"];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Faculty" : rows});
			}
		});
	});

	/*Retrieves all faculty that have the search term in their last name*/
		router.get("/users/faculty/:last_name",function(req,res){
		var query = "select * from faculty where `last name` like '%" + req.params.last_name + "%' order by `last name`";
		var table = [req.params.last_name];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Faculty" : rows});
			}
		});
	});	
	
	//Debug only, delete.
	router.get("/grades",function(req,res){
		var query = "SELECT * FROM grades";
		var table = ["grades"];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Grades" : rows});
			}
		});
	});
	
	
	//For getting the assignments of class.
	router.get("/courses/:course_id/section/:section_id/assignments",function(req,res){
		var query = "SELECT * FROM assignments WHERE `course id` =? AND `section number` =?";
		var table = [req.params.course_id, req.params.section_id];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Assignments" : rows});
			}
		});
	});
	
	/*This retrieves all grades for a specific user in a specific course. 
	Format get request like so:
	URL/api/users/grades/KSU_ID&COURSE_ID
	Example: http://localhost:3000/api/users/nwilso54/grades/IT4153
	*/
	router.get("/users/:ksu_id/grades/:course_id",function(req,res){
		var query = "SELECT `ksu id`, `course id`, `section number`, assignment, grade from grades INNER JOIN assignments ON grades.idassignment=assignments.idassignment WHERE `ksu id` =? AND `course id` =?";
		var table = [req.params.ksu_id, req.params.course_id];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"grades" : rows});
			}
		});
	});	
	
	
	/*This retrieves all the courses a student is enrolled in.
	Example: http://localhost:3000/api/users/courses/nwilso54
	*/
		router.get("/users/courses/:ksu_id",function(req,res){
		var query = "SELECT DISTINCT enrolled.`ksu id`, course.`course name`, enrolled.`course id`, enrolled.`section number` FROM enrolled INNER JOIN course ON enrolled.`course id`=course.`course id` WHERE `ksu id` = ?";
		var table = [req.params.ksu_id];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Courses" : rows});
			}
		});
	});
	
	/*For the announcements of a class.
	Example: http://localhost:3000/api/courses/IT4153/section/2/announcements
	*/
		router.get("/courses/:course_id/section/:section_id/announcements",function(req,res){
		var query = "select subject, announcement, date from announcements where `course id` = ? AND `section number` = ?";
		var table = [req.params.course_id, req.params.section_id];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Announcements" : rows});
			}
		});
	});
	
	/*For the professor to post an announcement to a class
	Example:
	
		router.post("/courses/:course_id/section/:section_id/announcements/:subject/:announcement",function(req,res){
		var query = "insert into announcements (`course id`, `section id`, date, subject, announcement) values (?course_id, ?section_id, current_date(), ?subject, ?announcement)";
		var table = [req.params.course_id, req.params.section_id, req.params.subject, req.params.announcement];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL insert"});
			} else {
				res.json({"Announcement posted"});
			}
		});
	});
	*/
	
	
	/*For the discussions list of a class.
	Example http://localhost:3000/api//courses/CS4850/section/1/discussionslist
	*/
		router.get("/courses/:course_id/section/:section_id/discussionslist",function(req,res){
			var query = "select * from discussionslist where `course id` = ? AND`section number` = ?";
			var table = [req.params.course_id, req.params.section_id];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Discussions list" : rows});
			}
		});
	});
	
	/*For the discussions in a given list.
	Example http://localhost:3000/api/discussionslist/1/discussions
	*/
		router.get("/discussionslist/:iddiscussionslist/discussions",function(req,res){
			var query = "select * from discussions where iddiscussionslist = ?";
			var table = [req.params.iddiscussionslist];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Discussions" : rows});
			}
		});
	});
	
	/*For the replies to a given discussion.
	Eample http://localhost:3000/api/discussionslist/1/discussions/1
	*/
		router.get("/discussionslist/:iddiscussionslist/discussions/:discussionid",function(req,res){
			var query = "select * from discussionreplies where discussionid = ?";
			var table = [req.params.discussionid];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Replies" : rows});
			}
		});
	});

	/*For events happening on or after current day.
	Eample http://localhost:3000/api/events
	*/
		router.get("/events",function(req,res){
			var query = "select * from events where date >= CURDATE()";
			var table = ["events"];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Events" : rows});
			}
		});
	});

	/*For list of bulidlings on a given campus.
	Eample http://localhost:3000/api/buildings/campus/kennesaw
	or http://localhost:3000/api/buildings/campus/marietta
	*/
		router.get("/buildings/campus/:campus",function(req,res){
			var query = "select * from buildings where campus=?";
			var table = [req.params.campus];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Buildings" : rows});
			}
		});
	});	
	
	/*For list of bulidlings that match a search term.
	Eample 
	*/
		router.get("/buildings/:building_name",function(req,res){
		var query = "select * from buildings where `building name` like '%" + req.params.building_name +"%'";
		var table = ["req.params.building_name"];
		query = mysql.format(query, table);
		connection.query(query,function(err, rows){
			if(err){
				res.json({"Error" : true, "Message" : "Error executing MySQL query"});
			} else {
				res.json({"Buildings" : rows});
			}
		});
	});	
	
}




module.exports = REST_ROUTER;