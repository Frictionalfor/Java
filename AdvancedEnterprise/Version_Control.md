# Version Control

## What is Version Control?

Version control tracks **changes to files over time**. Like "Save As" but smarter - you can go back to any version, see who changed what, and collaborate with others.

Think of it like **Google Docs version history** but for code.

## Git - The Most Popular VCS

**Git** is a **distributed version control system**.

- Every developer has full copy of repository
- Work offline, sync later
- Branching and merging made easy
- Industry standard

## Key Concepts

### Repository (Repo)
Project folder with all files and version history.

```
repo/
  | .git/           (Git metadata)
  | src/
  | pom.xml
  | README.md
```

### Commit
Snapshot of changes at a point in time.

```
Commit 1: Initial project
Commit 2: Add login feature
Commit 3: Fix bug in login
Commit 4: Add registration
```

### Branch
Parallel line of development.

```
main branch:     o---o---o---o
                       \
feature branch:         o---o---o
```

## Basic Git Commands

### Initialize Repository
```bash
git init
```

### Clone Repository
```bash
git clone https://github.com/username/project.git
```

### Check Status
```bash
git status
```

### Stage Changes
```bash
git add filename.java   # Stage specific file
git add .               # Stage all changes
```

### Commit Changes
```bash
git commit -m "Add login feature"
```

### View History
```bash
git log --oneline
```

### View Differences
```bash
git diff          # Unstaged changes
git diff --staged # Staged changes
```

## Branching

### Create Branch
```bash
git branch feature-login
```

### Switch Branch
```bash
git checkout feature-login
```

### Create and Switch (Shortcut)
```bash
git checkout -b feature-login
```

### List Branches
```bash
git branch
```

### Delete Branch
```bash
git branch -d feature-login
```

### Merge Branch
```bash
git checkout main
git merge feature-login
```

## Remote Repositories

### Add Remote
```bash
git remote add origin https://github.com/username/project.git
```

### View Remotes
```bash
git remote -v
```

### Push Changes
```bash
git push origin main
```

### Pull Changes
```bash
git pull origin main
```

### Clone
```bash
git clone https://github.com/username/project.git
```

## Git Workflow

### 1. Make Changes
```bash
# Edit files
git status
```

### 2. Stage Changes
```bash
git add .
```

### 3. Commit
```bash
git commit -m "Add user authentication"
```

### 4. Push
```bash
git push origin main
```

## Common Workflows

### Feature Branch Workflow
```bash
git checkout main
git pull origin main
git checkout -b feature/user-auth
# ... make changes ...
git add .
git commit -m "Add authentication"
git push origin feature/user-auth
# Create Pull Request on GitHub
```

### Daily Workflow
```bash
git pull origin main     # Get latest
# ... work ...
git add .
git commit -m "Daily changes"
git push origin main
```

## .gitignore

Tell Git which files to ignore.

```
# Ignore IDE files
.idea/
.vscode/

# Ignore build output
target/
build/

# Ignore dependencies
node_modules/

# Ignore environment files
.env
*.log
```

## Undoing Changes

### Undo Working Directory Changes
```bash
git checkout -- filename.java
```

### Unstage File
```bash
git reset HEAD filename.java
```

### Amend Last Commit
```bash
git commit --amend -m "New message"
```

### Revert Commit
```bash
git revert abc123
```

## GitHub Flow

1. **Create branch** for feature
2. **Make changes** and commit
3. **Push** to GitHub
4. **Create Pull Request**
5. **Review** and discuss
6. **Merge** to main
7. **Deploy**

## Git Best Practices

1. **Commit often**: Small, focused commits
2. **Write clear messages**: Describe what and why
3. **Pull before push**: Stay updated
4. **Use branches**: Don't work directly on main
5. **Review code**: Use Pull Requests
6. **Don't commit secrets**: Add .env to .gitignore

## Common Commands Cheat Sheet

| Command | Description |
|---------|-------------|
| `git init` | Initialize repository |
| `git clone` | Clone repository |
| `git status` | Check status |
| `git add` | Stage changes |
| `git commit` | Save changes |
| `git push` | Upload to remote |
| `git pull` | Download from remote |
| `git branch` | List/create branches |
| `git checkout` | Switch branches |
| `git merge` | Merge branches |
| `git log` | View history |
| `git diff` | View changes |

## Key Takeaways

1. Git tracks changes to files
2. Commit = snapshot of changes
3. Branch = parallel development
4. Always pull before push
5. Use branches for features
6. Write meaningful commit messages
7. Don't commit sensitive data

## Practice

1. Initialize git repo in your project
2. Make commits with meaningful messages
3. Create feature branch and merge
4. Push to GitHub
5. Create Pull Request
6. Use .gitignore
