package kz.stier.financetracing.service;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;
import jakarta.transaction.Transactional;
import kz.stier.financetracing.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
@Slf4j
public class ParsingService {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");

    @Autowired
    private TransactionService transactionService;

    @Transactional
    public void parsingPdf(MultipartFile file) throws ParseException, IOException {

        InputStream is = file.getInputStream();
        PdfDocument pdf = new PdfDocument(is);
        PdfTableExtractor extractor = new PdfTableExtractor(pdf);

        for (int pageIndex = 0; pageIndex < pdf.getPages().getCount(); pageIndex++) {
            PdfTable[] tableLists = extractor.extractTable(pageIndex);
            if (tableLists.length > 0) {
                if (pageIndex == 0) {
                    saveTransactions(tableLists[2], 1);
                } else {
                    for (PdfTable table : tableLists) {
                        saveTransactions(table, 0);
                    }
                }
            }
        }
    }

    private void saveTransactions(PdfTable table, int x) throws ParseException {
        for (int i = x; i < table.getRowCount(); i++) {
            Transaction row = new Transaction();
            for (int j = 0; j < table.getColumnCount(); j++) {
                switch (j) {
                    case 0:
                        row.setDate(dateFormat.parse(table.getText(i, j).trim()));
                        break;
                    case 1:
                        String str = StringUtils.deleteWhitespace(table.getText(i, j));
                        int index = str.indexOf("₸");
                        row.setAmount(Double.parseDouble(str.substring(1, index).replace(',', '.')));
                        break;
                    case 2:
                        row.setTransactionType(table.getText(i, j).trim());
                        break;
                    case 3:
                        row.setDetail(table.getText(i, j).trim());
                        break;
                    default:
                        break;
                }
            }
            transactionService.save(row);
        }
    }
}
