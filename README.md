# SJP GEO — projeto Android demonstrativo

## O que este pacote é
Projeto Android nativo **compilável**, com a interface HTML V8 incorporada num WebView e um serviço nativo de localização durante a sessão do técnico. O GPS é iniciado após login e autorização de localização, com notificação persistente. Os dados do serviço nativo são salvos **apenas no dispositivo**. O GPS exibido pelo HTML depende também das permissões do WebView.

**Não é um APK pronto.** Este ambiente não tem Android SDK/Gradle para gerar ou testar um APK. A compilação está configurada no GitHub Actions.

## Limitações fundamentais
- Sem API e banco compartilhado: o gestor **não** acompanha outros celulares. A tela HTML guarda dados locais do aparelho.
- O serviço nativo de GPS registra posições locais, **não transmite** coordenadas a servidor. Não existe rastreamento remoto em tempo real.
- A localização pode falhar ou ser interrompida por falta de sinal, bateria, permissões ou restrições do Android. O usuário pode revogar permissões nas configurações.
- A demonstração usa credenciais fixas; não usar dados reais ou distribuir publicamente como sistema municipal.
- A sessão não possui gestão de expediente institucional nem autenticação segura. O serviço de localização é iniciado pelo login do técnico e encerrado ao sair; ao parar o aplicativo abruptamente pode haver necessidade de encerrar o serviço pelo Android.
- Não testado em dispositivo nem compilado neste ambiente. O GitHub Actions pode requerer ajustes conforme o SDK e dependências disponíveis.

## Compilar pelo GitHub (sem Replit)
1. Crie um repositório **privado** no GitHub.
2. Envie **o conteúdo extraído** do ZIP para a raiz do repositório, incluindo a pasta `.github`.
3. Abra **Actions** > **Compilar APK SJP GEO** > **Run workflow**.
4. Aguarde o resultado; na execução bem-sucedida, baixe o artefato `SJP-GEO-Tecnico-APK`.
5. Extraia o ZIP do artefato para encontrar `app-debug.apk`. Instale somente em dispositivo de teste e confira as permissões.

## Próximo desenvolvimento para uso real
API autenticada, banco de dados, upload seguro de evidências, sincronização de posições com intervalo e carimbo temporal, tratamento de offline, autenticação por perfil, auditoria, alertas de localização desatualizada, gestão do expediente, LGPD e testes de campo. Gerar APK de produção assinado após homologação.
