# Version Control with Git

## Basic Commands

### Setup
```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### Initialize Repository
```bash
git init
```

### Daily Workflow
```bash
# Add files to staging
git add .
git add filename.java

# Commit changes
git commit -m "Add new feature"

# View status
git status
git log --oneline
```

### Branching
```bash
# Create new branch
git branch feature-branch
git checkout feature-branch

# Or create and switch
git checkout -b feature-branch

# Merge branch
git checkout main
git merge feature-branch
```

### Remote Repositories
```bash
# Add remote
git remote add origin https://github.com/username/repo.git

# Push changes
git push origin main

# Pull changes
git pull origin main
```

### Common Workflows
1. **Feature Branch Workflow**: Create branch for each feature
2. **GitHub Flow**: Simple branch-based workflow
3. **Git Flow**: More structured with develop/release branches

## GitHub Workflow
1. Fork repository
2. Clone locally
3. Create feature branch
4. Make changes
5. Push to GitHub
6. Create Pull Request
7. Review and merge
