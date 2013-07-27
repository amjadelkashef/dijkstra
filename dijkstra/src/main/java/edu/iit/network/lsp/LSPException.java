package edu.iit.network.lsp;

@SuppressWarnings("serial")
public class LSPException extends RuntimeException {

	public LSPException()
	{
	}

	public LSPException(String message)
	{
		super(message);
	}

	public LSPException(Throwable cause)
	{
		super(cause);
	}

	public LSPException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
