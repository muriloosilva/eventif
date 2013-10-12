<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html" charset="UTF-8">


<!--                                                               -->
<!-- Consider inlining CSS to reduce the number of requested files -->
<!--                                                               -->
<link type="text/css" rel="stylesheet" href="Secitec2013.css">
<link type="text/css" rel="stylesheet" href="css/index.css">
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">

<!--                                           -->
<!-- Any title is fine                         -->
<!--                                           -->
<title>Secitec 2013</title>

<!--                                           -->
<!-- This script loads your compiled module.   -->
<!-- If you add any GWT meta tags, they must   -->
<!-- be added before this line.                -->
<!--                                           -->
<script type="text/javascript" language="javascript" src="secitec2013.nocache.js"></script><script defer="defer">secitec2013.onInjectionDone('secitec2013')</script>
<link rel="stylesheet" href="http://secitecifgformosa.com.br/secitec2013/gwt/clean/clean.css"><script>window["_GOOG_TRANS_EXT_VER"] = "1";</script></head>

<!--                                           -->
<!-- The body can have arbitrary html, or      -->
<!-- you can leave the body empty if you want  -->
<!-- to create a completely dynamic UI.        -->
<!--                                           -->
<body>

	<!-- OPTIONAL: include this if you want history support -->
	<iframe src="javascript:''" id="__gwt_historyFrame" tabindex="-1" style="position: absolute; width: 0; height: 0; border: 0"></iframe>

	<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
	<noscript>
		&lt;!-- &lt;div
			style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif"&gt;
			Your web browser must have JavaScript enabled in order for this
			application to display correctly.&lt;/div&gt; --&gt;
	</noscript>

	<div id="main">
		<div id="banner"></div>
<!-- 		<div id="menu"> -->
<!--			<div id="menuHorizontal" class="aMenuHorizontal"><table cellspacing="0" cellpadding="0"><tbody><tr><td align="left" style="vertical-align: top;"><a class="gwt-Anchor aMenuHorizontal" href="javascript:;">Apresenta√ß√£o</a></td><td align="left" style="vertical-align: top;"><a class="gwt-Anchor aMenuHorizontal" href="javascript:;">Programa√ß√£o</a></td><td align="left" style="vertical-align: top;"><a class="gwt-Anchor aMenuHorizontal" href="javascript:;" aria-hidden="true" style="display: none;">Minhas Atividades</a></td><td align="left" style="vertical-align: top;"><a class="gwt-Anchor aMenuHorizontal" href="javascript:;" aria-hidden="false">Login ou Cadastre-se</a></td><td align="left" style="vertical-align: top;"><a class="gwt-Anchor aMenuHorizontal" href="javascript:;">Fale Conosco</a></td><td align="left" style="vertical-align: top;"><table cellspacing="0" cellpadding="0" class="hpUsuario" aria-hidden="true" style="display: none;"><tbody><tr><td align="right" style="vertical-align: top;"><div class="gwt-Label aMenuUsuario">Usu√°rio:</div></td><td align="right" style="vertical-align: top;"><div class="gwt-Label nomeUsuario"></div></td><td align="right" style="vertical-align: top;"><a class="gwt-Anchor aMenuUsuario" href="javascript:;">Sair</a></td></tr></tbody></table></td></tr></tbody></table></div>-->
<!--			<div id="menuDir" class="aMenuDir"></div>-->
<!-- 		</div> -->
		<div id="corpo">
			<% 
			
				int res = Integer.parseInt(request.getParameter("res"));
				String msg;
				if(res == 0){
					msg = "O seu cadastro j· est· confirmado.";
				}
				else if(res == 1){
					msg = "O seu cadastro foi confirmado.";
				}
				
				else{
					msg="N„o poi possivel confirmar o cadastro.";
				}
			
			%>
		
			<div id="corpoEsq"><table cellspacing="5" cellpadding="0" border="0" class="vpMainPageContent" style="width: 100%; height: 100%;"><tbody><tr><td align="left" style="vertical-align: middle;"><table cellspacing="0" cellpadding="0" border="0" style="width: 100%; height: 24px;"><tbody><tr><td align="center" style="vertical-align: middle;"><div class="lbTitle">ConfirmaÁ„o de Cadastro</div></td></tr></tbody></table></td></tr><tr><td align="left" style="vertical-align: middle;"><table cellspacing="0" cellpadding="0" border="0" class="conteudoApresentacao" style="width: auto;"><tbody><tr><td align="left" style="vertical-align: middle;"><div><div class="gwt-HTML">
			<p><%=msg %></p><a href="http://secitecifgformosa.com.br">Clique aqui para voltar ao site</a></div></div></td></tr></tbody></table></td></tr></tbody></table></div>
			<div id="corpoDir"><table cellspacing="5" cellpadding="0" border="0" class="vpMainPageContent" style="width: 100%; height: 100%;"><tbody><tr><td align="center" style="vertical-align: middle;"><div style="width: auto; height: auto;"></div></td></tr></tbody></table></div>
		</div>
	</div>


<iframe src="javascript:''" id="secitec2013" tabindex="-1" style="position: absolute; width: 0px; height: 0px; border: none;"></iframe></body></html>