import java.sql.Date;


public class HtmlPage
{
	private int id;
	private byte[] compressedHtml; 
	private DomainUrl domainUrl; 
	private Date created;
	public HtmlPage(String html, DomainUrl domainUrl, Date created) throws Exception 
	{
		super();
		this.compressedHtml = new CompressionUtils().Compress(html);
		this.domainUrl = domainUrl;
		this.created = created;
	} 
	/*
	 * save html pages to database
	 */
	public void save()
	{ 
		
	}
	public int getId() {
		return id;
	}
	public byte[] getCompressedHtml() {
		return compressedHtml;
	}
	public DomainUrl getDomainUrl() {
		return domainUrl;
	}
	public Date getCreated() {
		return created;
	}
	
}
