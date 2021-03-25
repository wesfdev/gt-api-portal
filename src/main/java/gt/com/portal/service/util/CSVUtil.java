/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.service.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author usuario
 */
public class CSVUtil {

    public static final String UTF_8 = "UTF-8";
    public static final String TYPE = "text/csv";

    public CSVUtil() throws Exception {
        throw new Exception("Utility class");
    }

    public static boolean isCSV(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static Iterable<CSVRecord> getRecords(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, UTF_8));
                CSVParser parser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            return parser.getRecords();
        } catch (IOException e) {
            throw new RuntimeException("Fail parse CSV file: ", e);
        }
    }

}
