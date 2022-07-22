package tn.esprit.vbank.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import java.io.File;
import java.io.IOException;
import tn.esprit.vbank.entities.Post;
import tn.esprit.vbank.services.IPostService;

@Service
public class StaticticJob {

	@Autowired
	private IPostService iPostService;

	@Scheduled(cron = "0 45 22 * * SUN")
	public void generateStaticticReport() {

	}

	public void export() throws DocumentException, IOException, URISyntaxException {
		Document document = new Document(PageSize.A4);
		// PdfWriter.getInstance(document, response.getOutputStream());
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		// PdfWriter.getInstance(document, new
		// FileOutputStream("C:\\document\\StatisticPostReport"+currentDateTime+".pdf"));
		PdfWriter.getInstance(document, new FileOutputStream("C:\\document\\PostReport" + ".pdf"));

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLACK);

		Paragraph p = new Paragraph("Statistic Post Report", font);

		p.setAlignment(Paragraph.ALIGN_CENTER);
		Font font2 = FontFactory.getFont(FontFactory.COURIER, 11, Color.BLACK);
		document.add(p);
		Long idPost = (long) iPostService.getIdPostWithMaxComments();
		Post postWithMaxComment = iPostService.getPostById(idPost);
		Chunk chunk = new Chunk("Post With max comments :" + "\n", font2);
		Chunk chun = new Chunk("Nbr Comments : " + postWithMaxComment.getListComment().size() + "\n", font2);

		Chunk chunk1 = new Chunk("Post Title : " + postWithMaxComment.getTitle() + "\n", font2);
		Chunk chunk2 = new Chunk("Post Content : " + postWithMaxComment.getBody() + "\n", font2);

		document.add(chunk);
		document.add(chun);

		document.add(chunk1);
		document.add(chunk2);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 1.5f, 3.0f });
		table.setSpacingBefore(10);

		document.add(table);

		document.close();

	}

	public static void HistogramChart() throws IOException {
		double[] values = { 95, 49, 14, 59, 50, 66, 47, 40, 1, 67, 12, 58, 28, 63, 14, 9, 31, 17, 94, 71, 49, 64, 73,
				97, 15, 63, 10, 12, 31, 62, 93, 49, 74, 90, 59, 14, 15, 88, 26, 57, 77, 44, 58, 91, 10, 67, 57, 19, 88,
				84 };

		HistogramDataset dataset = new HistogramDataset();
		dataset.addSeries("key", values, 20);

		JFreeChart histogram = ChartFactory.createHistogram("JFreeChart Histogram", "Data", "Frequency", dataset);

		ChartUtils.saveChartAsPNG(new File("C://document//histogram.png"), histogram, 600, 400);
	}
}
