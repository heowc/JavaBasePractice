package com.heowc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

class ZipTest {

    private static final Logger logger = LoggerFactory.getLogger(ZipTest.class);

    @TempDir
    Path tempDir;

    @Test
    void createZipFileWithSingleFile() throws Exception {
        String actual = tempDir.resolve("singlefile.zip").toString();
        new ZipFile(actual).addFile(getFile("./test.txt"));

        assertThat(tempDir).isDirectoryContaining(path -> path.toString().equals(actual));
    }

    @Test
    void createZipFileWithMultipleFile() throws Exception {
        String actual = tempDir.resolve("multiplefile.zip").toString();
        new ZipFile(actual).addFiles(List.of(getFile("./test.txt"), getFile("./foo.txt")));

        assertThat(tempDir).isDirectoryContaining(path -> path.toString().equals(actual));
    }

    @Test
    void createZipFileWithCompression() throws Exception {
        // no compression
        String noCompressionPath = tempDir.resolve("no_compression.zip").toString();
        ZipParameters noCompressionParameters = new ZipParameters();
        noCompressionParameters.setCompressionMethod(CompressionMethod.STORE);
        new ZipFile(noCompressionPath).addFile(getFile("./test.txt"), noCompressionParameters);

        // compression
        String compressionPath = tempDir.resolve("compression.zip").toString();
        ZipParameters compressionParameters = new ZipParameters();
        new ZipFile(compressionPath).addFile(getFile("./test.txt"), compressionParameters);

        assertThat(tempDir).isDirectoryContaining(path -> path.toString().equals(noCompressionPath));
        assertThat(tempDir).isDirectoryContaining(path -> path.toString().equals(compressionPath));

        assertThat(new File(noCompressionPath)).satisfies(file -> {
            File other = new File(compressionPath);
            logger.info("no compression file length: {}", file.length()); // 125
            logger.info("compression file length: {}", other.length());  // 127
            assertThat(file.length()).isNotEqualTo(other.length());
        });
    }

    @Test
    void extractZipFileWithEncryptionThenThrowZipException() throws Exception {
        String actual = tempDir.resolve("passoword.zip").toString();
        ZipParameters parameters = new ZipParameters();
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
        new ZipFile(actual, "password".toCharArray()).addFile(getFile("./test.txt"), parameters);

        assertThatThrownBy(() -> {
            new ZipFile(actual).extractAll(tempDir.toString());
        }).isInstanceOf(ZipException.class)
          .hasMessage("Wrong password!");
    }

    @Test
    void extractZipFileWithEncryption() throws Exception {
        String actual = tempDir.resolve("passoword2.zip").toString();
        ZipParameters parameters = new ZipParameters();
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
        new ZipFile(actual, "password".toCharArray()).addFile(getFile("./test.txt"), parameters);

        new ZipFile(actual, "password".toCharArray()).extractAll(tempDir.toString());
    }

    private static File getFile(String path) {
        return new File(getResource(path).getFile());
    }

    private static URL getResource(String path) {
        return ZipTest.class.getClassLoader().getResource(path);
    }
}
