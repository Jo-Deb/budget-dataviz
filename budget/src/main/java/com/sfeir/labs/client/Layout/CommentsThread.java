package com.sfeir.labs.client.Layout;


import static com.google.common.base.Strings.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
/**
 * Comments thread widget: you should call {@link #showComments(String)} the first time you want it to load and {@link #reload(String)} every time it should
 * display a different thread
 */

public class CommentsThread extends Composite {
	private final HTMLPanel div = new HTMLPanel("");
	private boolean isLoaded;
	private String identifier;
	public CommentsThread(String disqusShortName) {
		initWidget(div);
		div.getElement().setId("disqus_thread");
		ScriptLoader.exportGlobalVar("disqus_shortname", disqusShortName);
		if (!GWT.isProdMode()) {
			ScriptLoader.exportGlobalVar("disqus_developer", "1");
		}
	}
	
	public void showComments(String identifier) {
		this.identifier = identifier;
		if (nullToEmpty(identifier).isEmpty()) {
			div.getElement().setInnerText("");
			return;
		}
		
		//real reload should be deferred to avoid getting the wrong href
		Scheduler.get().scheduleDeferred(reloadCommand);
	}
	private final ScheduledCommand reloadCommand = new ScheduledCommand() {
		@Override
		public void execute() {
			final String href = Window.Location.getHref();
			GWT.log("href : " + href);
			if (!isLoaded) {
				
				ScriptLoader.exportGlobalVar("disqus_identifier", identifier);
				//ScriptLoader.exportGlobalVar("budgetdataviz", href);
				ScriptLoader.exportGlobalVar("disqus_url", href);
				ScriptLoader.loadScript("http://budgetdata.disqus.com/embed.js", "disqus_js");
				//ScriptLoader.loadScript("http://scolarite-dataviz.disqus.com/embed.js", "disqus_js");
				isLoaded = true;
				
			} else {
				reload(identifier, href);
			}
		}
	};


	private native void reload(String identifier, String currentUrl)/*-{
		$wnd.DISQUS.reset({
		reload : true,
		config : function() {
		this.page.identifier = identifier;
		this.page.url = currentUrl;
		}
	});
	}-*/;
}