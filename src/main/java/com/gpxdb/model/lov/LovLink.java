package com.gpxdb.model.lov;

public class LovLink {

		private long lovlinkid;
		private String title;
		private String lang;
		private String href;
		private String rel;
		private String text;
		private String type;
		private long valueObjectId;

		public String toHTMLString(){
			StringBuffer sb = new StringBuffer();
			sb.append("<a href='" + this.href +"'");
			
			if(this.valueObjectId>0)
				sb.append(" id='" + Long.toString(this.valueObjectId) + "'");
			
			if(this.lang!=null && this.lang.length()>0)
				sb.append(" lang='" + this.lang +"'");

			if(this.rel!=null && this.rel.length()>0)
				sb.append(" rel='" + this.rel +"'");
			
			if(this.title!=null && this.title.length()>0)
				sb.append(" title='" + this.title +"'");
			
			if(this.type!=null && this.type.length()>0)
				sb.append(" type='" + this.type +"'");
			
			sb.append(">" + this.text + "</a>");
			return sb.toString();
		}
		
		public LovLink() {
			super();
		}
		
		/**
		 * @return the valueObjectId
		 */
		public long getValueObjectId() {
			return valueObjectId;
		}

		/**
		 * @param valueObjectId the valueObjectId to set
		 */
		public void setValueObjectId(long valueObjectId) {
			this.valueObjectId = valueObjectId;
		}
		
		public LovLink(long lovlinkid, String title, String lang, String href,
				String text, String type) {
			super();
			this.lovlinkid = lovlinkid;
			this.title = title;
			this.lang = lang;
			this.href = href;
			this.text = text;
			this.type = type;
		}
		
		public long getLovLinkid() {
			return lovlinkid;
		}
		public void setLovLinkid(long lovlinkid) {
			this.lovlinkid = lovlinkid;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getLang() {
			return lang;
		}
		public void setLang(String lang) {
			this.lang = lang;
		}
		public String getHref() {
			return href;
		}
		public void setHref(String href) {
			this.href = href;
		}
		public String getRel() {
			return rel;
		}
		public void setRel(String rel) {
			this.rel = rel;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
}
