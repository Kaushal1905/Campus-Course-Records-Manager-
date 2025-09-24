package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupService {

    public void createBackup(String sourcePath, String backupBasePath) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupDir = Paths.get(backupBasePath, "backup_" + timestamp);
        
        try {
            Files.createDirectories(backupDir);
            Files.list(Paths.get(sourcePath))
                .forEach(file -> {
                    try {
                        Files.copy(file, backupDir.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.err.println("Failed to copy file: " + file.getFileName());
                    }
                });
            System.out.println("Backup created at: " + backupDir);
        } catch (IOException e) {
            System.err.println("Failed to create backup: " + e.getMessage());
        }
    }

    public long calculateBackupSize(Path backupPath) throws IOException {
        if (!Files.exists(backupPath)) {
            return 0;
        }
        return Files.walk(backupPath)
            .filter(Files::isRegularFile)
            .mapToLong(p -> {
                try {
                    return Files.size(p);
                } catch (IOException e) {
                    return 0L;
                }
            })
            .sum();
    }
}