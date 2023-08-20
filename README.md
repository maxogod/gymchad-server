# gymchad-server

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
