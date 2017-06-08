package Bean;

public class PetSitterBean {
	
	private int m_iNo;
	private String m_strUserID;
	private String m_strTitle;
	private String m_strDate;
	private String m_strTerm;
	private String m_strFeedback;
	private boolean m_bClose;
	private boolean m_bVisible;
	
	public PetSitterBean()
	{
		m_iNo = -1;
		m_strUserID = "";
		m_strTitle = "";
		m_strDate = "";
		m_strTerm = "";
		m_strFeedback = "";
		m_bClose = false;
		m_bVisible = true;
	}
	
	public void setNo( int iNo )
	{
		m_iNo = iNo;
	}
	
	public void setUserID( String strUserID )
	{
		m_strUserID = strUserID;
	}
	
	public void setTitle( String strTitle )
	{
		m_strTitle = strTitle;
	}
	
	public void setDate( String strDate )
	{
		m_strDate = strDate;
	}
	
	public void setTerm( String strTerm )
	{
		m_strTerm = strTerm;
	}
	
	public void setFeedback( String strFeedback )
	{
		m_strFeedback = strFeedback;
	}
	
	public void setClose( boolean bClose )
	{
		m_bClose = bClose;
	}
	
	public void setVisible( boolean bVisible )
	{
		m_bVisible = bVisible;
	}
	
	public String getUserID()
	{
		return m_strUserID;
	}
	
	public String getTitle()
	{
		return m_strTitle;
	}
	
	public String getDate()
	{
		return m_strDate;
	}
	
	public String getTerm()
	{
		return m_strTerm;
	}
	
	public String getFeedback()
	{
		return m_strFeedback;
	}
	
	public boolean getClose()
	{
		return m_bClose;
	}
	
	public boolean getVisible()
	{
		return m_bVisible;
	}
	
}
