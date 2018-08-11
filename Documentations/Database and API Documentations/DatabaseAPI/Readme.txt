====================================================================================

  _  _____ _   _    ___  ___    ___       _        _                     _   ___ ___ 
 | |/ / __| | | |  / __|/ _ \  |   \ __ _| |_ __ _| |__  __ _ ______    /_\ | _ \_ _|
 | ' <\__ \ |_| | | (_ | (_) | | |) / _` |  _/ _` | '_ \/ _` (_-< -_)  / _ \|  _/| | 
 |_|\_\___/\___/   \___|\___/  |___/\__,_|\__\__,_|_.__/\__,_/__|___| /_/ \_\_| |___|
                                                                                     
====================================================================================

EC2 Instance IP: 13.59.236.94:3000
All requests will be fomatted like so: 13.59.236.94:3000/api/
To test a specific call and to see the information easily, it is recommended you use Postman. Or just type the URL into your browser.

Specific URL paths will be discussed later, but that is the basic template for formatting a request.
If the examples are used and do not return a response, it means that the server is down. Steps have been taken to ensure this does not happen, but notify me
immediately and I will restore it manually.


This readme assumes two things:  That the nodejs server is currently running on the EC2 instance, and that you are familiar with HTTP requests.
Everything is accessible in a 'tiered' fashion. What does this mean?

	It means that you will move through the information one step at a time. In order to see a student's classes, they must first be logged in. (And by extension, you have their KSU ID)
	By having them logged in, they will be able to move through their class information by using their KSU ID.
	Before they can see class announcements, they must click on a class. You then request the announcements based on the class they clicked (Course ID and Section Number).
	Before they can see all the discussions, they need to click on a discussion topic (or Discussion List).
	Before they can see the replies, they must first click on the discussion.
	
	Think of them as steps, one leading into the other.
	
	
	
Unless noted otherwise, all of these API calls are going to be using HTTP GET methods and will return data in JSON format.	
They are listed in order of information you would need to access the next step.

====================================================================================

    _   ___ ___    ___      _ _    
   /_\ | _ \_ _|  / __|__ _| | |___
  / _ \|  _/| |  | (__/ _` | | (_-<
 /_/ \_\_| |___|  \___\__,_|_|_/__/
                                   

====================================================================================
13.59.236.94:3000/api/users/KSU_ID&PASSWORD
	Example: 13.59.236.94:3000/api/users/nwilso54&1234
	This retrieves a specific user and will be our method of 'faking' authentication and true login functionality. If no matching ksu id and password are found,
	the response will be a blank JSON.
	
13.59.236.94:3000/api/users/faculty
	This retrieves a list of all faculty and orders them by last name.

13.59.236.94:3000/api/users/faculty/:last_name
	Example: 13.59.236.94:3000/api/users/faculty/her
	This retrieves a list of all faculty that have a last name matching the search term.	

13.59.236.94:3000/api/users/courses/:ksu_id
	Example: 13.59.236.94:3000/api/users/courses/nwilso54
	This returns a list of all classes a user is enrolled in.
	
13.59.236.94:3000/api/grades
	Another debug/testing method. Returns every grade in the system, should not be kept in final product.

13.59.236.94:3000/api/users/grades/KSU_ID&COURSE_ID
	Example: 13.59.236.94:3000/api/users/nwilso54/grades/IT4153
	Returns every grade for a student in a given course.
	
13.59.236.94:3000/api/courses/:course_id/section/:section_id/announcements
	Example: 13.59.236.94:3000/api/courses/IT4153/section/2/announcements
	Returns all the announcements for a given course and section, since courses can have multiple sections.

====================================================================================

  ___  _                  _            ___                   _   
 |   \(_)_____ _  _ _____(_)___ _ _   | __|__ _ _ _ __  __ _| |_ 
 | |) | (_-< _| || (_-<_-< / _ \ ' \  | _/ _ \ '_| '  \/ _` |  _|
 |___/|_/__|__|\_,_/__/__/_\___/_||_| |_|\___/_| |_|_|_\__,_|\__|
                                                                 
	
====================================================================================	
	
Discussions are all going to be bundled under one entry here.	

13.59.236.94:3000/api/courses/:course_id/section/:section_id/discussionslist
	This gives you the list of discussions for a specific class.
	
13.59.236.94:3000/api/discussionslist/:iddiscussionslist/discussions
	This gives you all of the discussions for a given discussion list (or dicussion topic, if you prefer).

13.59.236.94:3000/api/discussionslist/:iddiscussionslist/discussions/:discussionid
	This gives you all of the replies to a given discussion.
====================================================================================

  __  __               _           _   ___             _      
 |  \/  |__ _ _ __    /_\  _ _  __| | | __|_ _____ _ _| |_ ___
 | |\/| / _` | '_ \  / _ \| ' \/ _` | | _|\ V / -_) ' \  _(_-<
 |_|  |_\__,_| .__/ /_/ \_\_||_\__,_| |___|\_/\___|_||_\__/__/
             |_|                                              


====================================================================================	

	
The following is intended to be used in conjunction with the Google Maps API for the interactive map.
13.59.236.94:3000/api/events
	This gives you all of the events that are happening on or after the current day; no past events will be returned. Their location is also returned.

13.59.236.94:3000/api/buildings/campus/:campus
	This returns all of the buildings on a given campus as well as their addresses, as dictated by KSU. Campus can either be Marietta or Kennesaw

13.59.236.94:3000/api/buildings/:building_name
	Example: 13.59.236.94:3000/api//buildings/bur
	(This would return 'Burruss' on the Kennesaw campus)
	This returns all of the buildings that match a search term. The search term is the name of a building.
	
====================================================================================

    _   _              _   
   /_\ | |__  ___ _  _| |_ 
  / _ \| '_ \/ _ \ || |  _|
 /_/ \_\_.__/\___/\_,_|\__|
                           

====================================================================================

Developed by Nicholas Wilson using Express JS.
Hosted on an AWS EC2 t2.micro instance, running Debian GNU-Linux 9 (Stretch).
Database is MariaDB	 (A community developed fork of MySQL Relational Database, licensed under GNU to remain free).