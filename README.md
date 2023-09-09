# gymchad-server

[(checkout the frontend)](https://github.com/maxogod/gymchad-app)

~ Website to organize different fitness activities such as dancing/leg day/boxing and the exercises that belong to that activity, including images of the exercise, a brief description or annotation, sets/reps or duration time.

~ For people like me, who always forget what good exercises they can do for each different activity (or muscle group), and want a more **aethetic** visual representation of them instead of writing them all on paper or something boring like that.

~ **Important: Website is hosted on [render.com](https://render.com/) free tier, so it spins down when nobody's using it and spins back up when a user enters the page but it takes a few minutes to see the page if you are that user.**

## Endpoints:

### /api/auth

The first two endpoints will open a new session in the db for the user

* POST /register (needs email, googleId, name, picture)
* POST /login (needs email and googleId)
* GET /session (to get the user that is currently logged in)
* GET /logout (closes the open session and removes it from the db)

### /api/activity

* POST / (creates a new activity for the session user, needs name, banner and a list of exercises)
* GET / (gets all activities of session user)
* GET /<activityId> (gets the specific activity if it belongs to the user)
* PUT /<activityId> (update activity info, needs same fields as activity creation except for exercises)
* POST /add-exercise/<activityId> (adds exercise to the activity)
* DELETE /<activityId> (deletes the activity from the user)

### /api/exercises

* PUT /<exerciceId> (edit exercise)
* DELETE /<exerciceId> (deletes exercise from its activity)
