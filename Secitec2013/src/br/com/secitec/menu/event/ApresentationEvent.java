package br.com.secitec.menu.event;

import com.google.gwt.event.shared.GwtEvent;

public class ApresentationEvent extends GwtEvent<ApresentationEventHandler> {
	public static Type<ApresentationEventHandler> TYPE = new Type<ApresentationEventHandler>();
	private static String name = "";

	public ApresentationEvent(String name) {
		ApresentationEvent.name = name;
	}

	public static String getNameEvent() {
		return name;
	}

	@Override
	public Type<ApresentationEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ApresentationEventHandler handler) {
		handler.onApresentation(this);
	}
}
