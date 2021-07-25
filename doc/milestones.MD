# Mars Explorer App Milestones

1. Create Main Activity
2. Create Rover Photos List Fragment:
- utilizes RecyclerView for photos for selected Rover
- should be open when the app starts
- user should be able to select different cameras (using popup Menu available in toolbar on three dots toolbar button click), available for that rover
- after selecting Rover/Rover Camera - that selection is saved in SharedPreferences to initially show that selection by default after starting the app
- shoul utilize Motion Layout to show initial animation immediately after start
- user should be able to star the photo. If the user is not authenticated - The login screen should be opened. After the user authenticates successfully - user action (i.e. starring photo) should be executed
3. Create auth screen
- should have ability to authenticate user with email and with Google account.
4. Create Starred Photos fragment
5. Create Top Rated Photos fragment
6. Create Photo View fragment
7. Create WorkManager job which daily shows Notification about new Top rated photo if it has changed sinse last update
