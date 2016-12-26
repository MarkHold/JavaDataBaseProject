# Java SQL Database

The purpose of this application is to be able to read JSON objects from a textfile and add them into a database. The database
that will be used in SQLite. The way the application works is that it reads the file, parses the JSON object into a java object
, creates a table with different fields that match the fields in the JSON objects and then inserts the information from each field
from the object into its respective place in the table.

There is also a query method that will run queries on the database to extract information. Since the file that will be used to test this
application is will contain the data from a singel month of Reddit posts. The queries will extract information like "how many comments
have been posted that include the word: exactly". 

Keywords: SQLite, Quieries, Backend development. 
