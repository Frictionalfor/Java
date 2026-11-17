// State
let currentFile = null;
let allFiles = [];
let filteredFiles = [];

// DOM Elements
const fileList = document.getElementById('fileList');
const viewMode = document.getElementById('viewMode');
const editMode = document.getElementById('editMode');
const editBtn = document.getElementById('editBtn');
const saveBtn = document.getElementById('saveBtn');
const cancelBtn = document.getElementById('cancelBtn');
const status = document.getElementById('status');
const searchInput = document.getElementById('searchInput');

// Simple Markdown Parser
function parseMarkdown(text) {
    let html = text
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/```(\w+)?\n([\s\S]*?)```/g, (match, lang, code) => {
            const language = lang ? `language-${lang}` : '';
            const trimmedCode = code.trim();
            return `<pre><code class="${language}">${trimmedCode}</code></pre>`;
        })
        .replace(/`([^`]+)`/g, '<code>$1</code>')
        .replace(/^###### (.*$)/gim, '<h6>$1</h6>')
        .replace(/^##### (.*$)/gim, '<h5>$1</h5>')
        .replace(/^#### (.*$)/gim, '<h4>$1</h4>')
        .replace(/^### (.*$)/gim, '<h3>$1</h3>')
        .replace(/^## (.*$)/gim, '<h2>$1</h2>')
        .replace(/^# (.*$)/gim, '<h1>$1</h1>')
        .replace(/\*\*\*(.*?)\*\*\*/g, '<strong><em>$1</em></strong>')
        .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
        .replace(/\*(.*?)\*/g, '<em>$1</em>')
        .replace(/^&gt; (.*$)/gim, '<blockquote>$1</blockquote>')
        .replace(/\|(.+)\|/g, (match) => {
            const cells = match.split('|').filter(c => c.trim());
            if (cells.every(c => /^[-:]+$/.test(c.trim()))) return match;
            const row = cells.map(c => `<td>${c.trim()}</td>`).join('');
            return `<tr>${row}</tr>`;
        })
        .replace(/^\s*[-+*] (.*$)/gim, '<li>$1</li>')
        .replace(/^\s*\d+\. (.*$)/gim, '<li>$1</li>')
        .replace(/\[([^\]]+)\]\(([^)]+)\)/g, '<a href="$2">$1</a>')
        .replace(/\n\n/g, '</p><p>')
        .replace(/\n/g, '<br>');
    
    html = '<p>' + html + '</p>';
    html = html.replace(/<p><li>/g, '<li>').replace(/<\/li><\/p>/g, '</li>');
    html = html.replace(/<p><tr>/g, '<tr>').replace(/<\/tr><\/p>/g, '</tr>');
    html = html.replace(/<p><pre>/g, '<pre>').replace(/<\/pre><\/p>/g, '</pre>');
    html = html.replace(/<p><blockquote>/g, '<blockquote>').replace(/<\/blockquote><\/p>/g, '</blockquote>');
    html = html.replace(/(<li>.*?<\/li>)/s, '<ul>$1</ul>');
    html = html.replace(/<\/ul>\s*<ul>/g, '');
    html = html.replace(/(<tr>.*?<\/tr>)/s, '<table>$1</table>');
    html = html.replace(/<\/table>\s*<table>/g, '');
    
    return html;
}

// Get file icon based on extension
function getFileIcon(filename) {
    const ext = filename.split('.').pop().toLowerCase();
    switch (ext) {
        case 'md': return '[MD]';
        case 'java': return '[JAVA]';
        case 'py': return '[PY]';
        case 'js': return '[JS]';
        case 'html': return '[HTML]';
        case 'css': return '[CSS]';
        case 'json': return '[JSON]';
        default: return '[FILE]';
    }
}

// Group files by folder
function groupFilesByFolder(files) {
    const groups = {};
    
    files.forEach(file => {
        const parts = file.split('/');
        const folder = parts.length > 1 ? parts[0] : 'Root';
        const fileName = parts[parts.length - 1];
        
        if (!groups[folder]) {
            groups[folder] = [];
        }
        groups[folder].push({ path: file, name: fileName });
    });
    
    return groups;
}

// Render file list grouped by folder
function renderFileList() {
    fileList.innerHTML = '';
    
    if (filteredFiles.length === 0) {
        fileList.innerHTML = '<div class="tree-empty">No files found</div>';
        return;
    }
    
    const groups = groupFilesByFolder(filteredFiles);
    const sortedFolders = Object.keys(groups).sort((a, b) => a.localeCompare(b));
    
    sortedFolders.forEach(folder => {
        const files = groups[folder];
        
        // Folder header
        const folderHeader = document.createElement('div');
        folderHeader.className = 'tree-folder';
        folderHeader.innerHTML = `
            <span class="tree-toggle">▼</span>
            <span class="tree-folder-icon">[+]</span>
            <span class="tree-folder-name">${folder} (${files.length})</span>
        `;
        fileList.appendChild(folderHeader);
        
        // Files container
        const filesContainer = document.createElement('div');
        filesContainer.className = 'tree-children expanded';
        
        files.forEach(fileInfo => {
            const fileDiv = document.createElement('div');
            fileDiv.className = 'tree-file';
            fileDiv.dataset.path = fileInfo.path;
            const icon = getFileIcon(fileInfo.name);
            fileDiv.innerHTML = `
                <span class="tree-file-icon">${icon}</span>
                <span class="tree-file-name">${fileInfo.name}</span>
            `;
            fileDiv.addEventListener('click', () => loadFile(fileInfo.path, fileDiv));
            filesContainer.appendChild(fileDiv);
        });
        
        fileList.appendChild(filesContainer);
    });
}

// Load file list
async function loadFiles() {
    try {
        const response = await fetch('/api/files');
        if (!response.ok) throw new Error('API returned ' + response.status);
        allFiles = await response.json();
        filteredFiles = [...allFiles];
        
        console.log('Loaded', allFiles.length, 'files');
        renderFileList();
    } catch (error) {
        console.error('Error loading files:', error);
        fileList.innerHTML = '<div class="tree-loading">Error loading files: ' + error.message + '</div>';
    }
}

// Search functionality
searchInput.addEventListener('input', (e) => {
    const query = e.target.value.toLowerCase().trim();
    
    if (!query) {
        filteredFiles = [...allFiles];
    } else {
        filteredFiles = allFiles.filter(file => {
            const parts = file.split('/');
            const filename = parts[parts.length - 1].toLowerCase();
            const folderPath = parts.slice(0, -1).join('/').toLowerCase();
            return filename.includes(query) || folderPath.includes(query);
        });
    }
    
    renderFileList();
});

// Escape HTML for raw code display
function escapeHtml(text) {
    return text
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;');
}

// Load file content
async function loadFile(filepath, element) {
    document.querySelectorAll('.tree-file').forEach(el => el.classList.remove('active'));
    if (element) {
        element.classList.add('active');
    }
    
    currentFile = filepath;
    viewMode.style.opacity = '0.5';
    viewMode.style.transform = 'translateY(10px)';
    
    try {
        const response = await fetch(`/api/file/${filepath}`);
        const data = await response.json();
        
        if (data.error) {
            viewMode.innerHTML = `<div class="empty-state"><h2>Error</h2><p>${data.error}</p></div>`;
            viewMode.style.opacity = '1';
            viewMode.style.transform = 'translateY(0)';
            return;
        }
        
        setTimeout(() => {
            const ext = filepath.split('.').pop().toLowerCase();
            
            if (ext === 'java') {
                // Display Java files with syntax highlighting
                const escapedCode = escapeHtml(data.content);
                viewMode.innerHTML = `<pre><code class="language-java">${escapedCode}</code></pre>`;
            } else {
                // Display markdown files normally
                viewMode.innerHTML = parseMarkdown(data.content);
            }
            
            viewMode.style.display = 'block';
            viewMode.style.opacity = '1';
            viewMode.style.transform = 'translateY(0)';
            editMode.style.display = 'none';
            editBtn.style.display = 'inline-block';
            saveBtn.style.display = 'none';
            cancelBtn.style.display = 'none';
            status.textContent = '';
            status.className = 'status';
            if (typeof Prism !== 'undefined') {
                Prism.highlightAll();
            }
        }, 150);
        
    } catch (error) {
        viewMode.innerHTML = `<div class="empty-state"><h2>Error</h2><p>Failed to load file</p></div>`;
        viewMode.style.opacity = '1';
        viewMode.style.transform = 'translateY(0)';
    }
}

// Edit mode
editBtn.addEventListener('click', async () => {
    if (!currentFile) return;
    
    try {
        const response = await fetch(`/api/file/${currentFile}`);
        const data = await response.json();
        
        if (data.error) {
            status.textContent = 'Error loading file';
            status.className = 'status error';
            return;
        }
        
        editMode.value = data.content;
        viewMode.style.display = 'none';
        editMode.style.display = 'block';
        editBtn.style.display = 'none';
        saveBtn.style.display = 'inline-block';
        cancelBtn.style.display = 'inline-block';
        status.textContent = 'Editing mode';
        status.className = 'status';
        editMode.focus();
    } catch (error) {
        status.textContent = 'Error loading file';
        status.className = 'status error';
    }
});

// Save file
saveBtn.addEventListener('click', async () => {
    if (!currentFile) return;
    
    try {
        const response = await fetch(`/api/file/${currentFile}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ content: editMode.value })
        });
        
        const data = await response.json();
        
        if (data.error) {
            status.textContent = 'Error saving: ' + data.error;
            status.className = 'status error';
            return;
        }
        
        status.textContent = 'Saved successfully!';
        status.className = 'status success';
        
        viewMode.style.opacity = '0';
        viewMode.style.transform = 'translateY(10px)';
        
        setTimeout(() => {
            const ext = currentFile.split('.').pop().toLowerCase();
            
            if (ext === 'java') {
                const escapedCode = escapeHtml(editMode.value);
                viewMode.innerHTML = `<pre><code class="language-java">${escapedCode}</code></pre>`;
            } else {
                viewMode.innerHTML = parseMarkdown(editMode.value);
            }
            
            viewMode.style.display = 'block';
            viewMode.style.opacity = '1';
            viewMode.style.transform = 'translateY(0)';
            editMode.style.display = 'none';
            editBtn.style.display = 'inline-block';
            saveBtn.style.display = 'none';
            cancelBtn.style.display = 'none';
            if (typeof Prism !== 'undefined') {
                Prism.highlightAll();
            }
        }, 150);
        
        setTimeout(() => {
            status.textContent = '';
            status.className = 'status';
        }, 2000);
    } catch (error) {
        status.textContent = 'Error saving file';
        status.className = 'status error';
    }
});

// Cancel edit
cancelBtn.addEventListener('click', () => {
    viewMode.style.display = 'block';
    viewMode.style.opacity = '1';
    viewMode.style.transform = 'translateY(0)';
    editMode.style.display = 'none';
    editBtn.style.display = 'inline-block';
    saveBtn.style.display = 'none';
    cancelBtn.style.display = 'none';
    status.textContent = '';
    status.className = 'status';
});

// Keyboard shortcuts
document.addEventListener('keydown', (e) => {
    if (e.ctrlKey && e.key === 's' && editMode.style.display === 'block') {
        e.preventDefault();
        saveBtn.click();
    }
    if (e.key === 'Escape' && editMode.style.display === 'block') {
        cancelBtn.click();
    }
    if ((e.ctrlKey && e.key === 'k') || (e.key === '/' && !e.ctrlKey && document.activeElement.tagName !== 'TEXTAREA')) {
        e.preventDefault();
        searchInput.focus();
    }
});

// Initialize
window.addEventListener('DOMContentLoaded', () => {
    loadFiles();
    
    document.querySelector('.container').style.opacity = '0';
    document.querySelector('.container').style.transform = 'translateY(20px)';
    
    setTimeout(() => {
        document.querySelector('.container').style.transition = 'all 0.6s cubic-bezier(0.4, 0, 0.2, 1)';
        document.querySelector('.container').style.opacity = '1';
        document.querySelector('.container').style.transform = 'translateY(0)';
    }, 100);
});
