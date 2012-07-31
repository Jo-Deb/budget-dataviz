package com.sfeir.labs.client.Layout;



//package com.sfeir.labs.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;

public class ScriptLoader {

public static void loadScript(final String scriptUrl, final String scriptId) {
Scheduler.get().scheduleDeferred(new ScheduledCommand() {
@Override
public void execute() {
final Element oldScript = Document.get().getElementById(scriptId);
if (oldScript != null) {
oldScript.removeFromParent();
}
final ScriptElement script = Document.get().createScriptElement();
script.setId(scriptId);
script.setSrc(scriptUrl);
Document.get().getBody().appendChild(script);
}
});
}

public static native void exportGlobalVar(String varName, String varValue)/*-{
$wnd[varName] = varValue;
}-*/;

}