Vous venez de télécharger le code qui accompagne "Les Cahiers du Programmeur : Java EE 5 (3e édition)".

Vous trouverez de l'aide supplémentaire sur l'application et son déploiement à l'adresse http://www.antoniogoncalves.org

# Répertoires

Après avoir décompressé ce fichier, vous obtenez les répertoires suivants :

       +
       +-README.md                  : le fichier que vous êtes en train de lire
       |
       +-barkbank
       |    +-src                	: code source de l'application barkbank
       |    |  +-webapp          	: fichiers constituant l'application web (html, gif...)
       |    |     +-WEB-INF         : configuration de l'application barkbank
       |    +-target
       |       +-generated-sources 	: fichiers générés pour les services web
       +-petex
       |    +-src                	: code source de l'application petex
       |    |  +-resources         	: fichiers constituant l'application web (html, gif...)
       |    |     +-WEB-INF         : configuration de l'application petex
       |    +-target
       |       +-generated-sources	: fichiers générés pour les services web
       +-petstore
       |    +-Business            	: code métier de l'application petstore (stateless, stateful, entity, MDB...)
       |    +-ear                 	: module Maven pour packager un ear
       |    +-Swing                 : code client de l'application petstore (Swing)
       |    |  +-lib              	: librairies externes permettant la compilation du projet
       |    +-Web                   : code de l'application web

Dans ce document, la variable `YAPS_HOME` fait référence à la racine de cette arboresence.


# Installation

Avant de d'exécuter l'application, assurez vous que vous avez les outils nécessaire installés:

* JDK
    * Installez une version du JDK égale ou supèrieure à 1.6_23 à partir de [http://www.oracle.com/technetwork/java/archive-139210.html]()
    * Positionnez la variable `JAVA_HOME`
    * Rajoutez le répertoire `%JAVA_HOME%\bin` dans votre PATH

* Maven
    * Installez une version de Maven égale ou supèrieure à 3.0.1 à partir de [http://maven.apache.org/download.html]()
    * Positionnez la variable `MAVEN_HOME`
    * Rajoutez le répertoire `%MAVEN_HOME%\bin` dans votre PATH

* GlassFish
    * Installez une version du serveur Glassfish égale ou supèrieure à v3.0.1-b22 à partir de [http://glassfish.java.net/public/downloadsindex.html]()
    * Positionnez la variable `GLASSFISH_HOME`
    * Rajoutez le répertoire `%GLASSFISH_HOME%\bin` dans votre PATH


#  Configurer

!!! Si vous voulez utilisez la base de données MySQL au lieu de Derby, reportez-vous à la section ci-dessous (Autres bases de données) !!!

Il faut maintenant configurer GlassFish et la base de données :

* Créez le domaine petstore dans GlassFish en tenant compte des mots de passe ci-dessous (le port d'admin est 8282)

        asadmin create-domain --adminport 8282 --instanceport 8080 --nopassword=true petstore

        Using port 8282 for Admin.
        Using port 8080 for HTTP Instance.
        Using default port 7676 for JMS.
        Using default port 3700 for IIOP.
        Using default port 8181 for HTTP_SSL.
        Using default port 3820 for IIOP_SSL.
        Using default port 3920 for IIOP_MUTUALAUTH.
        Using default port 8686 for JMX_ADMIN.
        Using default port 6666 for OSGI_SHELL.

    Vous devez alors voir apparaitre le répertoire `%GLASSFISH_HOME%\glassfish\domains\petstore`

* (Si par la suite vous voulez supprimer ce domaine, tapez la commande asadmin delete-domain petstore)

* Démarrez le serveur Glassfish :

        asadmin start-domain --verbose petstore

* Vérifier que le serveur fonctionne en allant sur :

        http://localhost:8080/
        http://localhost:8282/ (pour la console d'administration)

      Si votre serveur de démarre pas, consulter le fichier `%GLASSFISH_HOME%\glassfish\domains\petstore\logs\server.log`

* Démarrez la base de données

        asadmin start-database --dbhost=localhost --dbport=1527 --dbhome=%GLASSFISH_HOME%/javadb

        [exec] Database started in Network Server mode on host localhost and port 1527.
        ...
        [exec] Command start-database executed successfully.

* Créer un pool de connection à la base de données :

        asadmin --port 8282 create-jdbc-connection-pool --datasourceclassname org.apache.derby.jdbc.ClientDataSource  --restype javax.sql.XADataSource  --property portNumber=1527:serverName=localhost:User=app:Password=app:databaseName=bookJavaEE5DB:connectionAttributes=\;create\=true bookJavaEE5Pool

* Créer la base de données (en pingant la datasource)

        asadmin --port 8282 ping-connection-pool bookJavaEE5Pool

      nb: Vous devez voir alors apparaitre le répertoire `%GLASSFISH_HOME%\javadb\bookJavaEE5DB`

* Créer une source de données

        asadmin --port 8282 create-jdbc-resource --connectionpoolid bookJavaEE5Pool jdbc/bookJavaEE5DS

* Créez les ressources JMS nécessaires:
    * une ConnectionFactory :

        asadmin --port 8282 create-jms-resource --restype javax.jms.ConnectionFactory jms/bookJavaEE5ConnectionFactory

    * un Topic JMS :

        asadmin --port 8282 create-jms-resource --restype javax.jms.Topic --property Name=OrderTopic jms/topic/order

    * une Queue JMS :

        asadmin --port 8282 create-jms-resource --restype javax.jms.Queue --property Name=ClientQueueTopic jms/queue/client

* Créer le logger de l'application Petstore qui utilise `java.util.logging`

        asadmin --port 8282 set-log-levels org\.agoncal\.book\.javaee5\.barkbank=FINEST
        asadmin --port 8282 set-log-levels org\.agoncal\.book\.javaee5\.petex=FINEST
        asadmin --port 8282 set-log-levels org\.agoncal\.book\.javaee5\.petstore=FINEST


# Vérification

* Vérifier que le pool de connection "bookJavaEE5Pool" a été cré soit :

        asadmin --port 8282 list-jdbc-connection-pools
         __TimerPool
         DerbyPool
         bookJavaEE5Pool

    * ou par la console d'admin http://localhost:8282/ (dans l'arbre, déployez le noeud Resources>JDBC>Connection Pools)

* Vérifier que la source de données "bookJavaEE5DS" a été créé :

        asadmin --port 8282 list-jdbc-resources
         jdbc/__TimerPool
         jdbc/__default
         jdbc/bookJavaEE5DS

    * ou par la console d'admin (dans l'arbre, déployez le noeud Resources>JDBC>JDBC Resources)

* Vérifier que les ressources JMS ont été créés :

        asadmin --port 8282 list-jms-resources
         jms/topic/order
         jms/queue/client
         jms/bookJavaEE5ConnectionFactory

    * ou par la console d'admin (dans l'arbre, déployez les noeuds Resources>JMS Resources>Connection Factories et Destination Resources)

* Pour vérifier que le logger a bien été configuré

        asadmin --port 8282 list-log-levels

    * ou par la console d'admin (dans l'arbre, déployez les noeuds Configuration>Logger Settings>Log Levels)
    * Vous devez voir aparaitre le logger com.yaps.petstore, com.barkbank et com.petex


#  Deployez l'application

Maintenant que tout est configuré, il faut déployer les applications sur le serveur. Assurez-vous bien que GlassFish et Derby sont démarrés et configurés (section précèdente)

* Déployez l'application BarkBank

    * Construisez l'application en tapant `%YAPS_HOME%\Barbank> mvn clean package`. Ceci a pour effet de compiler l'application, de générer les artefacts pour les services web et de la packager dans le fichier `%YAPS_HOME%\BarkBank\target\barkbank-3.0.war
    * Déployez l'application sur le serveur en tapant `%YAPS_HOME%\Barbank> asadmin --port 8282 deploy --force target\barkbank-3.0.war`
    * Pour vérifier que le déploiement a bien été effectué, rendez-vous à l'adresse [http://localhost:8080/barkbank-3.0/]()

* Déployez l'application PetEx

    * Construisez l'application en tapant `%YAPS_HOME%\Petex> mvn clean package`. Ceci a pour effet de compiler l'application, de générer les artefacts pour les services web et de la packager dans le fichier `%YAPS_HOME%\PexEx\target\petex-3.0.war
    * Déployez l'application sur le serveur en tapant `%YAPS_HOME%\Petex> asadmin --port 8282 deploy --force target\petex-3.0.war
    * Pour vérifier que le déploiement a bien été effectué, rendez-vous à l'adresse [http://localhost:8080/petex-3.0/]()

* Déployez l'application Yaps

    * Installez le jar VSTM pour l'application Swing en tapant `%YAPS_HOME%\Petstore\Swing> mvn install:install-file -Dfile=lib\vstm-xscore-0.5.0.jar -DgroupId=org.vstm -DartifactId=vstm-xscore -Dversion=0.5.0 -Dpackaging=jar -DgeneratePom=true`
    * Construisez l'application en tapant `%YAPS_HOME%\Petstore> mvn clean package`. Ceci a pour effet de compiler l'application, de générer les artefacts pour les services web et de la packager dans les fichiers `%YAPS_HOME%\Petstore\ear\target\ear-3.0.ear`
    * Déployez l'application sur le serveur en tapant `%YAPS_HOME%\Petstore> asadmin --port 8282 deploy --force ear\target\ear-3.0.ear`. Cette tache déploie l'application et insère des données en base
    * Pour vérifier que le déploiement a bien été effectué, rendez-vous à l'adresse [http://localhost:8080/petstore-3.0/]()


#  Utilisez l'application

Une fois les trois applications déployées, vous pouvez les utiliser à l'adresse [http://localhost:8080/petstore-3.0/]()
Consulter le catalogue, créez vous un compte ou logger vous `job/job`. Achetez des animaux domestiques, passez commande et vous utiliserez alors les services web des application BarkBank (pour la vérification des cartes bancaires) et PetEx (avertit le transporteur).

Pour executer l'application Swing tapez `%YAPS_HOME%\Petstore\Swing> mvn exec:java -Dexec.mainClass="com.yaps.petstore.client.ui.PetstoreFrame"`


# Demarrer/Arreter les serveurs

* Derby

         asadmin start-database --dbhost=localhost --dbport=1527 --dbhome=%GLASSFISH_HOME%/javadb
         asadmin stop-database --dbhost=localhost --dbport=1527

* GlassFish

         asadmin start-domain --verbose petstore
         asadmin stop-domain petstore

# Consulter la bases de données Derby

Une fois la base de données Derby démarrée et l'application déployée, vous pouvez utiliser des commandes Derby pour consulter la structure des tables ainsi que leur contenu:

* Ajoutez le répertoire `%GLASSFISH_HOME%\javadb\bin` dans le PATH
* Tapez la commande `ij` pour utiliser les lignes de commandes
* Connectez vous à la base, consulter les tables et leurs données :

        ij> connect 'jdbc:derby://localhost:1527/bookJavaEE5DB';
        ij> show tables;
        ij> describe DBUSER.T_ADDRESS;
        ij> select * from DBUSER.T_CATEGORY;
        ij> disconnect;
        ij> exit;

Si vous utilisez un IDE pour vous connectez à la base de données `bookJavaEE5DB`, voici la configuration necessaire :

* Driver class : `org.apache.derby.jdbc.ClientDriver` (dans le fichier `%MAVEN_REPO%\org\apache\derby\derbyclient\10.6.1.0\derbyclient-10.6.1.0.jar`)
* URL de connexion : `jdbc:derby://localhost:1527/bookJavaEE5DB`
* User / Pwd : app / app

# Autres bases de données

Si vous voulez utiliser une autre base de données que Derby, voici ce qu'il faut faire.

## MySQL

Avant d'exécuter les scripts pour configurer les serveurs, il faut modifier certaines variables des fichiers ant (`admin.xml` et `build.xml`). Les fichiers contiennent déjà ces diffèrentes valeurs, il suffit donc de mettre en commentaires certaines variables et en décommenter d'autres.

* dans admin.xml

        <!--Pour Derby-->
        <!--<property name="db.port" value="1527"/>-->
        <!--<property name="db.datasource" value="org.apache.derby.jdbc.ClientDataSource"/>-->
        <!--<property name="db.url" value="jdbc:derby://${db.host}:${db.port}/${db.sid}"/>-->

        <!--Pour MySQL-->
        <property name="db.port" value="3306"/>
        <property name="db.datasource" value="com.mysql.jdbc.jdbc2.optional.MysqlXADataSource"/>
        <property name="db.url" value="jdbc:mysql://${db.host}:${db.port}/${db.sid}"/>

        (...)
        <path id="classpath">
          <!--Pour Derby-->
          <!--<pathelement location="${derby.lib}/derbytools.jar"/>-->
          <!--<pathelement location="${derby.lib}/derbyclient.jar"/>-->
          <!--<pathelement location="${derby.lib}/derby.jar"/>-->

          <!--Pour MySQL-->
          <pathelement location="${glassfish.lib}/mysql-connector-java-5.0.7-bin.jar"/>
        </path>

* dans build.xml

        <!--Derby-->
        <!--<property name="db.port" value="1527"/>-->
        <!--<property name="db.driver" value="org.apache.derby.jdbc.ClientDriver"/>-->
        <!--<property name="db.url" value="jdbc:derby://${db.host}:${db.port}/${db.sid}"/>-->

        <!--MySQL-->
        <property name="db.port" value="3306"/>
        <property name="db.driver" value="com.mysql.jdbc.Driver"/>
        <property name="db.url" value="jdbc:mysql://${db.host}:${db.port}/${db.sid}"/>

        (...)
        <path id="classpath">
          <pathelement location="${glassfish.lib}/appserv-deployment-client.jar"/>
          <pathelement location="${glassfish.lib}/appserv-rt.jar"/>
          <pathelement location="${glassfish.lib}/webservices-rt.jar"/>
          <pathelement location="${glassfish.lib}/appserv-admin.jar"/>
          <pathelement location="${glassfish.lib}/javaee.jar"/>
          <pathelement location="${glassfish.lib}/toplink-essentials-agent.jar"/>
          <pathelement location="${glassfish.lib}/toplink-essentials.jar"/>
          <pathelement location="${glassfish.lib}/install/applications/jmsra/imqjmsra.jar"/>

          <!--Derby-->
          <!--<pathelement location="${derby.lib}/derbyclient.jar"/>-->

          <!--MySQL-->
          <pathelement location="${glassfish.lib}/mysql-connector-java-5.0.7-bin.jar"/>
        </path>


#  Debogger l'application

* Pour debogger l'application avec un IDE il faut tout d'abord configurer votre IDE avec les paramètres suivants : `-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9009`
* Lancer le serveur en mode debug : `%YAPS_HOME%\ant -f admin.xml start-domain-debug`
* Certains IDE doivent compiler eux même les sources pour que le debug fonctionne. Si tel est le cas, compilez les source avec votre IDE et construisez l'application avec la tache `%YAPS_HOME%\ant build-debug`
* Deployez comme d'habitude avec `%YAPS_HOME%\ant deploy`

(ref : [http://java.sun.com/products/jpda/doc/conninv.html#Invocation]())


#  Résourdre les problêmes

* Si le serveur ne démarre pas car un port d'écoute est utilisé :
    * Vérifiez que le port est utilisé à l'aide de la commande > telnet localhost 3306 (3306 est le port à tester)
    * changez le numéro de port lors de la création du domaine en rajoutant la propriété `--domainproperties` (ex `--domainproperties domain.jmxPort=8989`)

* Vérifiez votre firewall ou antivirus, il se peut qu'ils bloquent un port d'écoute

        com.sun.appserv.server.ServerLifecycleException: Cannot bind to URL [rmi://test:7979/management/rmi-jmx-connector]: javax.naming.CommunicationException [Root exception is java.rmi.ConnectIOException: error during JRMP connection establishment; nested exception is:
            java.net.SocketTimeoutException: Read timed out]
            at com.sun.enterprise.admin.server.core.JmxConnectorLifecycle.onStartup(JmxConnectorLifecycle.java:127)
            at com.sun.enterprise.server.ApplicationServer.onStartup(ApplicationServer.java:326)
            at com.sun.enterprise.server.ondemand.OnDemandServer.onStartup(OnDemandServer.java:112)
            at com.sun.enterprise.server.PEMain.run(PEMain.java:326)
            at com.sun.enterprise.server.PEMain.main(PEMain.java:260)
            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
            at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
            at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
            at java.lang.reflect.Method.invoke(Method.java:585)
            at com.sun.enterprise.server.PELaunch.main(PELaunch.java:272)
        Caused by: java.io.IOException: Cannot bind to URL [rmi://test:7979/management/rmi-jmx-connector]: javax.naming.CommunicationException [Root exception is java.rmi.ConnectIOException: error during JRMP connection establishment; nested exception is:
            java.net.SocketTimeoutException: Read timed out]

* `CLI171 Command deploy failed : Deploying application in domain failed; javax.naming.NameNotFoundException`. Vérifiez que vous avez déployé votre pool de connexion et votre datasource

* `Operation 'pingConnectionPool' failed in 'resources' Config Mbean. Target exception message: This pool is not registered with the runtime environment`. Vérifiez que la base de donnée fonctionne. Si oui, arretez-la et redémarrez-la.

* `org.apache.derby.client.am.DisconnectException: java.security.PrivilegedActionException : Error opening socket to server localhost on port 1527 with message : null`. Vous essayez de déployer l'application alors que la base de données ne tourne pas (utilisez la tache start-db)

* Si vous obtenez une exception similaire à celle-ci:

         Caused by: javax.servlet.ServletException : java.lang.OutOfMemoryError: PermGen space
            at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:378)
            at javax.servlet.http.HttpServlet.service(HttpServlet.java:820)
            at org.apache.catalina.core.ApplicationFilterChain.servletService (ApplicationFilterChain.java:397)
            at org.apache.catalina.core.ApplicationDispatcher.doInvoke(ApplicationDispatcher.java:849)
            at org.apache.catalina.core.ApplicationDispatcher.invoke(ApplicationDispatcher.java :697)
            at org.apache.catalina.core.ApplicationDispatcher.processRequest(ApplicationDispatcher.java:532)
            at org.apache.catalina.core.ApplicationDispatcher.doForward(ApplicationDispatcher.java:465)
            at org.apache.catalina.core.ApplicationDispatcher.forward (ApplicationDispatcher.java:353)
            at com.sun.faces.context.ExternalContextImpl.dispatch(ExternalContextImpl.java:413)
            ... 31 more
         Caused by: java.lang.OutOfMemoryError: PermGen space

Vous pouvez augmenter la zone mémoire PermGen de la jvm de la manière suivante :

* éditer le fichier `%domain_home%/config/domain.xml`
* dans le tag `<java-config>`, rajouter `<jvm-options>-XX:MaxPermSize=128m</jvm-options>`

* Il vous faut un JDK pour compiler les JSP en servlet. Si vous avez ce message là : `org.apache.jasper.JasperException: PWC6345: There is an error in invoking javac.  A full JDK (not just JRE) is required`. Il faut que dans le fichier `%GLASSFISH_HOME%\config\asenv.bat` la variable `AS_JAVA` pointe vers le JDK et non le JRE.