# Streaming Application

This is a simple application that implements a streaming service with streams, streamers and users.


The streamers can delete their streams.


The users can "listen" to a stream, see all the streams and get recommendations based on what he has listened to.


The features are implemented by reading commands from a file.


## Commands:

**Streamers:**
 

>ADD



Will add a new stream.


>LIST


Will list all the streams of a streamer with a certain id.
 

>DELETE


Will delete a stream with a certain id.
\
\
\
**Users:**

>LIST
 

Will print what the user has listened to recently.


>LISTEN

The user will listen to a stream with a certain id.


>RECOMMEND


The user will get a recommendation based on what he has listened to.

>SURPRISE

The user will get a random recommendation of 3 streams.

## Details

The application will get the details of streams, streamers, users and save them in a database before the commands are executed.

The application will read the commands from a file given as an argument and will print the results in another file given as an argument.

The results will be printed in a JSON format.
