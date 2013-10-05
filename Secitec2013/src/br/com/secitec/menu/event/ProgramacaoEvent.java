package br.com.secitec.menu.event;

import com.google.gwt.event.shared.GwtEvent;

public class ProgramacaoEvent extends GwtEvent<ProgramacaoEventHandler> {
  public static Type<ProgramacaoEventHandler> TYPE = new Type<ProgramacaoEventHandler>();
  private static String name = "";
  public ProgramacaoEvent(String name){
	  ProgramacaoEvent.name = name;
  }
  
  public static String getNameEvent(){
	  return name;
  }
  
  @Override
  public Type<ProgramacaoEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ProgramacaoEventHandler handler) {
    handler.onProgramacao(this);
  }
}
