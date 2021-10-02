# Mars Explorer App Milestones

1. Create Main Activity
2. Create Nav Drawer. User should be able to select different rovers from Nav Drawer
3. Create Rover Photos List Fragment:
- utilizes RecyclerView for each sol thumbs for selected Rover - each entry is horizontal lists of 25 max thumbnails for the Sol.
- user can view all photos for Sol by clicking item button (should navigate to Photos List Fragment)
- should be open when the app starts
- user should be able to star the photo. If the user is not authenticated - The login screen should be opened. After the user authenticates successfully - user action (i.e. starring photo) should be executed
4. Create Photos List Fragment
- utilizes paging to view all rover photos for selected rover and sol
5. Create Photo details fragment
- should utilize Motion Layout to show initial animation immediately after start
6. Create WorkManager job which daily shows Notification about new photos

