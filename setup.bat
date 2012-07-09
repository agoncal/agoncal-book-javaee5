@rem Arret d'un domaine (petstore)
@rem =====================================================
@rem asadmin --port 8282 stop-domain petstore

rem Suppression d'un domaine (petstore)
@rem =====================================================
@rem asadmin delete-domain petstore

@rem Cr�ation d'un domaine (petstore)
@rem =====================================================
@rem asadmin create-domain --adminport 8282 --instanceport 8080 --nopassword=true petstore

@rem Using port 8282 for Admin.
@rem Using port 8080 for HTTP Instance.
@rem Using default port 7676 for JMS.
@rem Using default port 3700 for IIOP.
@rem Using default port 8181 for HTTP_SSL.
@rem Using default port 3820 for IIOP_SSL.
@rem Using default port 3920 for IIOP_MUTUALAUTH.
@rem Using default port 8686 for JMX_ADMIN.
@rem Using default port 6666 for OSGI_SHELL.

@rem D�marrage du domaine (petstore)
@rem =====================================================
@rem asadmin start-domain --verbose petstore

@rem D�marrage de la base de donn�es Derby
@rem asadmin start-database --dbhost=localhost --dbport=1527 --dbhome=%GLASSFISH_HOME%/javadb

@rem Cr�ation d'un pool de connexions (bookJavaEE5Pool)
@rem =====================================================
@rem asadmin --port 8282 create-jdbc-connection-pool --datasourceclassname org.apache.derby.jdbc.ClientDataSource  --restype javax.sql.XADataSource  --property portNumber=1527:serverName=localhost:User=dbuser:Password=dbpwd:databaseName=bookJavaEE5DB:connectionAttributes=\;create\=true bookJavaEE5Pool
@rem asadmin --port 8282 list-jdbc-connection-pools

@rem Cr�ation de la base de donn�es (ping sur bookJavaEE5Pool)
@rem =====================================================
@rem asadmin --port 8282 ping-connection-pool bookJavaEE5Pool

@rem Cr�ation d'une source de donn�es (jdbc/bookJavaEE5DS)
@rem =====================================================
@rem asadmin --port 8282 create-jdbc-resource --connectionpoolid bookJavaEE5Pool jdbc/bookJavaEE5DS
@rem asadmin --port 8282 list-jdbc-resources

@rem Cr�ation des ressources JMS (jms/bookJavaEE5ConnectionFactory et jms/topic/order)
@rem =====================================================
@rem asadmin --port 8282 create-jms-resource --restype javax.jms.ConnectionFactory jms/bookJavaEE5ConnectionFactory
@rem asadmin --port 8282 create-jms-resource --restype javax.jms.Topic --property Name=OrderTopic jms/topic/order
@rem asadmin --port 8282 list-jms-resources 

@rem Cr�ation des loggers
@rem =====================================================
@rem asadmin --port 8282 set-log-level com\.yaps\.petstore=FINEST
@rem asadmin --port 8282 set-log-level com\.barkbank=FINEST
@rem asadmin --port 8282 set-log-level com\.petex=FINEST
