# Quiz

Simple QUIZ application to demonstrate some andoid features
  - Timer
  - Splash Screen
  - Login Form
  - Action Menu
  - Progress Dialog
  - Pass Result to next Activity
  - POJO for Data model class
  - Usage of ArrayList
  - Text Blink - Drawable 
  - Layout designs
  - ActionBar options
  
  ## Git Command Line 
  - brew install git //Installing git
	- git --version
  - git init 
  - git clone https://github.com/pritamworld/Quiz.git
  - git status
  - git remote //Showing Your Remotes
	- git remote -v
  - git log
  
  ## Set new Remote URL OR Adding Remote Repositories
  
  - git remote add origin <url>
	- git remote add pb <url> //New 
  - git remote remove url
	- git remote set-url origin https://NewRepoLink.git
  
  ## Set User Name
  - git config --global user.name
  - git config --global user.name "Your Name"
  - git config --global user.name
  
  ## Adding file to stage
  - git add -A //Add all
  - git add <File name>
  - git add * //Add all
  
  ## Comitting changes to current branch
  - git commit -m "Your commmit message"
  - git commit -am "Your commmit message" //Stage and Commit together
  - git status
  
  ## Send current changes to github
  - git push  <REMOTENAME> <BRANCHNAME> 
  - git push origin master
  
  ## Working with branches
	- git checkout gh-pages
	- git merge master
	- git push
	- git push --all
	
  ## Fetching and Pulling from Your Remotes
  - git fetch <remote>

  ## Inspecting a Remote
  - git remote show origin

  ## How do I check out a remote Git branch?
  - git fetch
	- git checkout test

  ## To see the currently selected branch
  - git branch
	- git fetch
	- git checkout BranchName

  ## Creating a local branch from an existing branch (can be master/ develop/ any-other-branch).
	- git checkout -b branch_name

  ## Push this to remote
	- git push -u remote_name local_branch_name:remote_branch_name

  ## You can delete it with the -d option to git branch:
  - git branch -d hotfix

  
  ## Set User Email 
  - git config --list
  - git config user.email
  - git config --global user.email "Your Email address on Github"
  - git config user.email
  - git status
  - git add -A
  - git commit -m "Test update"
  - git push origin master
