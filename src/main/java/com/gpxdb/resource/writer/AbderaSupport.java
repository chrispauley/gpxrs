package com.gpxdb.resource.writer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
//import javax.ws.rs.ProduceMime;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;

@Provider
@Produces({ MediaType.APPLICATION_ATOM_XML})
@Consumes({ MediaType.APPLICATION_ATOM_XML})
public class AbderaSupport implements MessageBodyWriter<Object>,
		MessageBodyReader<Object> {

	private final static Abdera abdera = new Abdera();

	public static Abdera getAbdera() {
		return abdera;
	}

	public long getSize(Object arg0) {
		return -1;
	}

	public boolean isWriteable(Class<?> type) {
		return (Feed.class.isAssignableFrom(type) || Entry.class
				.isAssignableFrom(type));
	}

	public void writeTo(Object feedOrEntry, MediaType mediaType,
			MultivaluedMap<String, Object> headers, OutputStream outputStream)
			throws IOException {
		if (feedOrEntry instanceof Feed) {
			Feed feed = (Feed) feedOrEntry;
			Document<Feed> doc = feed.getDocument();
			doc.writeTo(outputStream);
		} else {
			Entry entry = (Entry) feedOrEntry;
			Document<Entry> doc = entry.getDocument();
			doc.writeTo(outputStream);
		}
	}

	public boolean isReadable(Class<?> type) {
		return (Feed.class.isAssignableFrom(type) || Entry.class
				.isAssignableFrom(type));
	}

	public Object readFrom(Class<Object> feedOrEntry, MediaType mediaType,
			MultivaluedMap<String, String> headers, InputStream inputStream)
			throws IOException {
		Document<Element> doc = getAbdera().getParser().parse(inputStream);
		Element el = doc.getRoot();
		if (feedOrEntry.isAssignableFrom(el.getClass())) {
			return el;
		} else {
			throw new IOException("Unexpected payload, expected "
					+ feedOrEntry.getName() + ", received "
					+ el.getClass().getName());
		}
	}

	
	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Object readFrom(Class<Object> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3, MultivaluedMap<String, String> arg4,
			InputStream arg5) throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public long getSize(Object arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4) {
		return -1;
	}

	
	public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void writeTo(Object arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4,
			MultivaluedMap<String, Object> arg5, OutputStream arg6)
			throws IOException, WebApplicationException {
		// TODO Auto-generated method stub

	}
}
