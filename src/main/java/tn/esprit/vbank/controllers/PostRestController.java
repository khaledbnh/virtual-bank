package tn.esprit.vbank.controllers;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import tn.esprit.vbank.entities.Comment;
import tn.esprit.vbank.entities.Post;
import tn.esprit.vbank.entities.User;
import tn.esprit.vbank.services.IPostService;
import tn.esprit.vbank.services.IUserService;
import tn.esprit.vbank.utils.PostValidator;
import tn.esprit.vbank.utils.StaticticJob;

@RestController
public class PostRestController {

	@Autowired
	private IPostService iPostService;

	@Autowired
	private IUserService iUserService;

	@PostMapping("/ajouterPost")
	@ResponseBody
	public ResponseEntity ajouterPost(@RequestBody Post a) {
		
		
		
		
		if(a.getTitle()==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't create a post without a title");

		}

		if (a.getPhoto() != null && !PostValidator.validateImage(a.getPhoto())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid image");

		}
		if (a.getVideo() != null && !PostValidator.validateVideo(a.getVideo())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid Video");

		}
		if(a.getUser()==null || iUserService.getUser(a.getUser().getId())==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found");

		}
		
		if(a.getPhoto()!=null) {
		//try {
		//	String contentImageDescription=PostValidator.detectImageNudity(a.getPhoto());
		//	if (contentImageDescription!=null) {
			//	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the image contains nudity content");

			//}
		//} catch (IOException e1) {
		//	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid image");

		//}
		}
		try {
			if(a.getBody()!=null) {
			String filtredBody = PostValidator.filterBadWorlds(a.getBody());
			a.setBody(filtredBody);
			}
			String filtredTitle = PostValidator.filterBadWorlds(a.getTitle());
			a.setTitle(filtredTitle);
		} catch (IOException e) { 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");

		}
		Post Post = null;
		a.setDateCreation(new Date());
		try {
			Post = iPostService.addPost(a);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(Post);

	}

	@RequestMapping(value = "/Posts", method = RequestMethod.GET)
	public ResponseEntity getPosts() {

		 //User user=new User();
		// user=iUserService.addUser(user);
		//Post post=new Post();
		// post.setBody("test");
		//post.setDateCreation(new Date());
		//post.setUser(user);
		//iPostService.addPost(post);
		return ResponseEntity.status(HttpStatus.OK).body(iPostService.getPosts());
	}

	@RequestMapping(value = "/supprimerPost/{id}", method = RequestMethod.DELETE)
	public ResponseEntity supprimerPost(@PathVariable Long id) {

		Boolean delete = false;
		try {
			delete = iPostService.supprimerPost(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		if (delete) {
			return ResponseEntity.status(HttpStatus.OK).body("post deleted");

		}
		return ResponseEntity.status(HttpStatus.OK).body("post not found");

	}

	@RequestMapping(value = "/modiferPost/{id}", method = RequestMethod.PUT)
	public ResponseEntity updatePost(@PathVariable Long id, @RequestBody Post a) {
		
		try {
			Post verifyPost = iPostService.getPostById(id);
			if(verifyPost==null) {
				return ResponseEntity.status(HttpStatus.OK).body("post not found");

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		
		if(a.getTitle()==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't create a post without a title");

		}

		if (a.getPhoto() != null && !PostValidator.validateImage(a.getPhoto())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid image");

		}
		if (a.getVideo() != null && !PostValidator.validateVideo(a.getVideo())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid Video");

		}
		if(a.getUser()==null || iUserService.getUser(a.getUser().getId())==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found");

		}

		try {
			if(a.getBody()!=null) {
			String filtredBody = PostValidator.filterBadWorlds(a.getBody());
			a.setBody(filtredBody);
			}
			String filtredTitle = PostValidator.filterBadWorlds(a.getTitle());
			a.setTitle(filtredTitle);
		} catch (IOException e) { 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");

		}
		Post Post = null;
		try {
			Post = iPostService.updatePost(id, a);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(Post);

	}

	@RequestMapping(value = "/PostByid/{id}", method = RequestMethod.GET)
	public ResponseEntity getPostById(@PathVariable Long id) {

		Post Post = null;
		try {
			Post = iPostService.getPostById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		if (Post != null) {
			return ResponseEntity.status(HttpStatus.OK).body(Post);

		}
		return ResponseEntity.status(HttpStatus.OK).body("post not found");

	}

	@RequestMapping(value = "/nbeCommentsByPost/{id}", method = RequestMethod.GET)
	public int getnbeCommentsByPost(@PathVariable Long id) {

		int nbe = 0;

		Post post = iPostService.getPostById(id);
		if (post != null) {
			nbe = post.getListComment().size();
		}

		return nbe;
	}

	@RequestMapping(value = "/getCommentsByPost/{id}", method = RequestMethod.GET)
	public ResponseEntity getCommentsByPost(@PathVariable Long id) {

		Post post = iPostService.getPostById(id);
		if (post != null) {
			return ResponseEntity.status(HttpStatus.OK).body(post.getListComment());

		}
		return ResponseEntity.status(HttpStatus.OK).body("post not found");

	}
	
	@RequestMapping(value = "/nbeSignalByPost/{id}", method = RequestMethod.GET)
	public int getnbeSignalsByPost(@PathVariable Long id) {

		int nbe = 0;

		Post post = iPostService.getPostById(id);
		if (post != null) {
			nbe = post.getListSignal().size();
		}

		return nbe;
	}

	@RequestMapping(value = "/getSignalsByPost/{id}", method = RequestMethod.GET)
	public ResponseEntity getSignalsByPost(@PathVariable Long id) {

		Post post = iPostService.getPostById(id);
		if (post != null) {
			return ResponseEntity.status(HttpStatus.OK).body(post.getListSignal());

		}
		return ResponseEntity.status(HttpStatus.OK).body("post not found");

	}
	@RequestMapping(value = "/StatisticPostReport", method = RequestMethod.GET)
	@Scheduled(cron = "0 33 00 * * MON")
	public  void export() throws DocumentException, IOException, URISyntaxException {
		Document document = new Document(PageSize.A4);
		//PdfWriter.getInstance(document, response.getOutputStream());
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());
		PdfWriter.getInstance(document, new FileOutputStream("C:\\document\\StatisticPostReport"+currentDateTime+".pdf"));
		//PdfWriter.getInstance(document, new FileOutputStream("C:\\document\\PostReport"+".pdf"));

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLACK);
		
		Paragraph p = new Paragraph("Statistic Post Report", font);

		p.setAlignment(Paragraph.ALIGN_CENTER);
		Font font2 = FontFactory.getFont(FontFactory.COURIER, 11, Color.BLACK);
		document.add(p);
		Font font3 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

		Long idPost=(long) iPostService.getIdPostWithMaxComments();
		Post postWithMaxComment=iPostService.getPostById(idPost);
		Chunk chunk1 = new Chunk("Post With max comments :"+ "\n", font3);
		Chunk chunk2 = new Chunk("Nbr Comments : " +postWithMaxComment.getListComment().size() + "\n", font2);
		Chunk chunk3 = new Chunk("Post Title : " +postWithMaxComment.getTitle() + "\n", font2);
		Chunk chunk4 = new Chunk("Post Content : "+postWithMaxComment.getBody()+ "\n", font2);
				
		document.add(chunk1);
		document.add(chunk2);
		document.add(chunk3);
		document.add(chunk4);

		Long idPostLike=(long) iPostService.getIdPostWithMaxLike();
		Post postWithMaxLike=iPostService.getPostById(idPostLike);
		Chunk chunkLike1 = new Chunk("\n"+"Post With max Likes :"+ "\n", font3);
		Chunk chunkLike2 = new Chunk("Nbr Likes : " +postWithMaxLike.getListComment().size() + "\n", font2);
		Chunk chunkLike3 = new Chunk("Post Title : " +postWithMaxLike.getTitle() + "\n", font2);
		Chunk chunkLike4 = new Chunk("Post Content : "+postWithMaxLike.getBody()+ "\n", font2);
		
		
		document.add(chunkLike1);
		document.add(chunkLike2);
		document.add(chunkLike3);
		document.add(chunkLike4);


		document.close();

		StaticticJob.HistogramChart();
	}
	
	@GetMapping("/statistic/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=StatisticPostReport_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        
         
        Document document = new Document(PageSize.A4);
		//PdfWriter.getInstance(document, response.getOutputStream());
		//DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
       // String currentDateTime = dateFormatter.format(new Date());
		//PdfWriter.getInstance(document, new FileOutputStream("C:\\document\\StatisticPostReport"+currentDateTime+".pdf"));
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLACK);
		
		Paragraph p = new Paragraph("Statistic Post Report", font);

		p.setAlignment(Paragraph.ALIGN_CENTER);
		Font font2 = FontFactory.getFont(FontFactory.COURIER, 11, Color.BLACK);
		document.add(p);
		Font font3 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

		Long idPost=(long) iPostService.getIdPostWithMaxComments();
		Post postWithMaxComment=iPostService.getPostById(idPost);
		Chunk chunk1 = new Chunk("Post With max comments :"+ "\n", font3);
		Chunk chunk2 = new Chunk("Nbr Comments : " +postWithMaxComment.getListComment().size() + "\n", font2);
		Chunk chunk3 = new Chunk("Post Title : " +postWithMaxComment.getTitle() + "\n", font2);
		Chunk chunk4 = new Chunk("Post Content : "+postWithMaxComment.getBody()+ "\n", font2);
				
		document.add(chunk1);
		document.add(chunk2);
		document.add(chunk3);
		document.add(chunk4);

		Long idPostLike=(long) iPostService.getIdPostWithMaxLike();
		Post postWithMaxLike=iPostService.getPostById(idPostLike);
		Chunk chunkLike1 = new Chunk("\n"+"Post With max Likes :"+ "\n", font3);
		Chunk chunkLike2 = new Chunk("Nbr Likes : " +postWithMaxLike.getListComment().size() + "\n", font2);
		Chunk chunkLike3 = new Chunk("Post Title : " +postWithMaxLike.getTitle() + "\n", font2);
		Chunk chunkLike4 = new Chunk("Post Content : "+postWithMaxLike.getBody()+ "\n", font2);
		
		
		document.add(chunkLike1);
		document.add(chunkLike2);
		document.add(chunkLike3);
		document.add(chunkLike4);


		document.close();
         
    }
	
	@RequestMapping(value = "/PostsWithStatusActive", method = RequestMethod.GET)
	public ResponseEntity getPostsWithStatusActive() {

		
		return ResponseEntity.status(HttpStatus.OK).body(iPostService.getListPostWithStatusActive());
	}
	
	@RequestMapping(value = "/PostsWithStatusDesActive", method = RequestMethod.GET)
	public ResponseEntity getPostsWithStatusDesActive() {


		return ResponseEntity.status(HttpStatus.OK).body(iPostService.getListPostWithStatusDesActive());
	}
}
