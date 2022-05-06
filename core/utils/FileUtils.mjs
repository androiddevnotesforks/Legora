/**
 * All Files Utils in this File Here to Generate Files
 */
export default function generateFileFromTemplate(data = {}, templateFile = "", generatedPath = "") {
    ejs.renderFile(templateFile, data, (err, result) => {
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
