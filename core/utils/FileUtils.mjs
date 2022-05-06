import ejs from 'ejs';
import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

/**
 * All Files Utils in this File Here to Generate Files
 */
export default function generateFileFromTemplate(data = {}, templateFile = "", generatedPath = "") {
    const __filename = fileURLToPath(import.meta.url);
    const __dirname = path.dirname(__filename);
    ejs.renderFile(__dirname + templateFile, data, (err, result) => {
        if (err) {
            console.log('info', 'error encountered: ' + err);
        } else {
            try {
                fs.writeFileSync(generatedPath, result, 'utf8');
            } catch (err) {
                if (err) {
                    throw err;
                }
            }

        }
    });
}

export function convertTextToInputString(value) {
    return "\"" + value + "\""
}