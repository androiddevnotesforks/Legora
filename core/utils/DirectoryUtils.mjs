import fs from 'fs';

/**
 * All Directory Utils in the Application To Generate Directories
 */
export function generateDirectory(directoryPath) {
    try {
        generateDirectoryInternal(directoryPath);
    } catch (error) {
        console.log("File Generate with Path : " + directoryPath + " Failed With Error : " + directoryPath)
    }
}

export function generateMultipleDirectories(directories = [""]) {
    for (let i = 0; i < directories.length; i++) {
        if (directories[i]) {
            generateDirectory(directories[i]);
        }
    }
}

export function generateDirectoryInternal(directoryPath) {
    try {
        return fs.mkdirSync(directoryPath)
    } catch (err) {
        if (err.code !== 'EEXIST') throw err
    }
}
