export class Log {
    static writeLog(message, level) {
        // this should be read from AppSettings.js or AppSettings.json
        const loggingLevel = 0;
        if (level > loggingLevel) {
            return;
        }
        console.log(message);
    }
}