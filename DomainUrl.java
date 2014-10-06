
public class DomainUrl 
{
	private String domainUrlHash; 
	private String domainUrl;
	private Domain domain; 
	
	public DomainUrl(String domainUrlHash, String domainUrl, Domain domain) {
		super();
		this.domainUrlHash = domainUrlHash;
		this.domainUrl = domainUrl;
		this.domain = domain; 
	}
	public String getDomainUrlHash() {
		return domainUrlHash;
	}
	public String getDomainUrl() {
		return domainUrl;
	} 
	public Domain getdomain() {
		return domain;
	} 
	
	
	
}
