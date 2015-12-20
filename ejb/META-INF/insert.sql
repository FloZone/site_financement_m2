INSERT INTO User VALUES (1, TRUE, 'admin@example.com', 'admin', 'admin', 'admin', '!#/)zW��C�JJ��');
INSERT INTO User VALUES (2, FALSE, 'user@example.com', 'user', 'user', 'user', '!#/)zW��C�JJ��');
INSERT INTO User VALUES (3, FALSE, 'jeanbob@pizza.com', 'Jean', 'Bob', 'jean-bob', '!#/)zW��C�JJ��');
INSERT INTO User VALUES (4, FALSE, 'coucou@cava.com', 'Marie', 'Rouana', 'herbe', '!#/)zW��C�JJ��');
INSERT INTO User VALUES (5, FALSE, 'yolo@swag.com', 'Sacha', 'Touille', 'youpi', '!#/)zW��C�JJ��');



INSERT INTO Category VALUES (1, 'Musique');
INSERT INTO Category VALUES (2, 'Film');
INSERT INTO Category VALUES (3, 'Communautaire');
INSERT INTO Category VALUES (4, 'Sport');
INSERT INTO Category VALUES (5, 'Scientifique');
INSERT INTO Category VALUES (6, 'Divertissement');



INSERT INTO Project VALUES (1, 'Le financement servira dans un premier temps à acquérir un laboratoire de recherche ultra hi-tech delamortquitue, ce qui representera à peu de chose près la moitié de cette campagne de financement. Il nous faudra aussi la cuisine de Maïté pour pouvoir cuisiner des croustades. Nous prévoyons aussi de racheter Andros (fournisseur de pommes). Nous souhaitons ouvrir site de fouille archéologiques, en effet, des traces de civilisations divines ont été découvertes et nous comptons là dessus pour prouver que la croustade était leur unique repas. Enfin, le financement servira a démontrer la théorie de la licorne matinale qui stipule que toute licorne se levant avec la tête dans l''arrière train en meurt immédiatement !', '2015-11-20 14:11:05.609', 'Ce projet conciste à financer une étude qui démontrera enfin la supériorité des croustade face aux licornes. Si toi aussi tu pries le dieu croustade tous les soirs, si tu ne vie que pour les pommes et que tu considères qu''avoir une corne sur la tête, c''est trop pas le swag, alors rejoins nous ! Je terminerai sur la citation d''un grand et nobe chevalier qui dit un jour: "la croustade, ce n''est plus de l''art, c est de l''érotisme !"', '/images/projets/croustade.jpg', '2016-01-19 14:11:05.573', 500, 'Etude sur la croustade', 3);
INSERT INTO Project VALUES (2, 'Avec le financement, nous pourrons louer un studio d''enregistrement et enregistrer l''album avec du vrai matériel professionnel. Ensuite nous pourrons faire appel à un dessinateur afin de faire la couverture de l''album. Si le financement atteint 3000€, nous pourrons même louer une salle pour faire un concert privé !.', '2015-10-10 19:35:05.609', 'Le but de ce projet est de créer notre premier album sur support physique ! Après 2 albums sur internet, nous voulons essayer de nous diversifier. N''hésitez pas à inviter vos amis !', '/images/projets/album.jpeg', '2016-01-05 01:00:00.000', 1500, 'Enregistrement d''un album', 5);
INSERT INTO Project VALUES (3, 'Nous avons besoin d''acheter du matériel: caméras, micros, lumières mais aussi des décors et des costumes.', '2015-08-01 14:11:05.609', 'Nous voulons tourner un court-métrage sur le thème de Star Wars. Il s''agit d''une adaptation du célèbre jeu-vidéo "Le Pouvoir de la Force" qui met en scène Starkiller, un apprenti seigneur Sith entrainé par Dark Vador afin d''éliminer les derniers survivant Jedi. Que la force soit avec vous !', '/images/projets/starwars.jpg', '2015-09-10 14:11:05.573', 2000, 'Court-métrage Star Wars', 1);
INSERT INTO Project VALUES (4, 'Louer les locaux pour le rassemblement', '2015-11-14 10:00:05.609', 'Un rassemblement pour effectuer une minute de silence puis une marche silencieuse dans les rues de Paris"', '/images/projets/paris.jpg', '2015-11-20 10:00:00.000', 100, 'Pray for Paris', 4);



INSERT INTO TopProject VALUES (1, 'Projet novateur sur le plan scientifique, n''hésitez pas à jeter un oeil :)', 1);
INSERT INTO TopProject VALUES (2, 'Petit groupe de musique indépendant et plein de talent !', 2);
INSERT INTO TopProject VALUES (3, 'Un projet sur Star Wars ? C''est forcément intéressant !', 3);



INSERT INTO Project_Category VALUES (1, 5);
INSERT INTO Project_Category VALUES (2, 1);
INSERT INTO Project_Category VALUES (2, 6);
INSERT INTO Project_Category VALUES (3, 2);
INSERT INTO Project_Category VALUES (3, 6);
INSERT INTO Project_Category VALUES (4, 3);



INSERT INTO Compensation VALUES (1, 10, 'Une croustade pour vous, chez vous. Devenez un demi-dieu !', 1);
INSERT INTO Compensation VALUES (2, 25, 'Une croustade ne vous suffit plus ? Recevez alors DEUX croustades et devenez un DIEUUUUU MOUAHAHAHAHAHA', 1);
INSERT INTO Compensation VALUES (3, 100, 'C''est incroyable, un arbre à croustades !', 1);
INSERT INTO Compensation VALUES (4, 499, 'Cette compensation sert uniquement à m''offrir la PS4, merci :)', 1);

INSERT INTO Compensation VALUES (5, 15, 'Un album dédicacé', 2);
INSERT INTO Compensation VALUES (6, 30, 'Compensation précédente + poster dédicacé', 2);
INSERT INTO Compensation VALUES (7, 50, 'Compensation précédente + making off', 2);
INSERT INTO Compensation VALUES (8, 100, 'Compensation précédente + venez nous voir pendant l''enregistrement de l''album', 2);

INSERT INTO Compensation VALUES (9, 20, 'Votre nom dans le générique', 3);
INSERT INTO Compensation VALUES (10, 50, 'Vous voulez assiter au tournage ? C''est par ici !', 3);
INSERT INTO Compensation VALUES (11, 100, 'Vous apparaitrez à l''écran comme figurant (qui n''a jamais rêvé d''être un wookie ?)', 3);
INSERT INTO Compensation VALUES (12, 500, 'Repartez avec l''un des sabres laser utilisé lors du tournage !', 3);

INSERT INTO Compensation VALUES (13, 10, 'Un simple don pour nous soutenir', 4);



INSERT INTO Donation VALUES (1, 100, '2015-11-24 21:54:00.814', 1, 4);
INSERT INTO Donation VALUES (2, 25, '2015-11-30 21:00:00.814', 1, 1);
INSERT INTO Donation VALUES (3, 10, '2015-11-30 21:00:00.814', 1, 2);

INSERT INTO Donation VALUES (4, 30, '2015-11-30 21:00:00.814', 2, 1);
INSERT INTO Donation VALUES (5, 100, '2015-11-30 21:00:00.814', 2, 2);
INSERT INTO Donation VALUES (6, 100, '2015-11-30 21:00:00.814', 2, 3);
INSERT INTO Donation VALUES (7, 100, '2015-11-30 21:00:00.814', 2, 4);

INSERT INTO Donation VALUES (8, 500, '2015-11-30 21:00:00.814', 3, 2);
INSERT INTO Donation VALUES (9, 500, '2015-11-30 21:00:00.814', 3, 3);
INSERT INTO Donation VALUES (10, 100, '2015-11-30 21:00:00.814', 3, 4);
INSERT INTO Donation VALUES (11, 500, '2015-11-30 21:00:00.814', 3, 5);
INSERT INTO Donation VALUES (12, 20, '2015-11-30 21:00:00.814', 3, 2);

INSERT INTO Donation VALUES (13, 20, '2015-11-30 21:00:00.814', 4, 3);
INSERT INTO Donation VALUES (14, 50, '2015-11-30 21:00:00.814', 4, 5);
INSERT INTO Donation VALUES (15, 50, '2015-11-30 21:00:00.814', 4, 1);



INSERT INTO Message VALUES (1, 0, 'Oh mon dieu, une croustade !', '2015-11-20 20:03:37.851', 3, 4, 1);
INSERT INTO Message VALUES (2, 0, 'Les licornes domineronts le monde !', '2015-11-20 20:30:37.851', 0, 5, 1);
INSERT INTO Answer VALUES (1, 0, 'Sombre quenouille, la croustade est le festin des dieux !', '2015-11-20 22:03:37.851', 1, 4, 2);

INSERT INTO Message VALUES (3, 0, 'Je veux venir vous voir !!', '2015-11-20 22:03:37.851', 2, 4, 2);
INSERT INTO Message VALUES (4, 0, 'Génial ! Depuis le temps que j''attends ça !', '2015-11-20 22:03:37.851', 2, 3, 2);

INSERT INTO Message VALUES (5, 0, 'Les compensations sont-elles cumulées ?', '2015-11-20 22:03:37.851', 2, 3, 3);
INSERT INTO Answer VALUES (2, 0, 'Absolument ! Chaque compensation donne droit à toutes les précédentes automatiquement :)', '2015-11-20 22:03:37.851', 10, 1, 5);
INSERT INTO Answer VALUES (3, 0, 'Super ! Je participe avec plaisir !', '2015-11-20 22:03:37.851', 0, 3, 5);
INSERT INTO Message VALUES (6, 0, 'Que la force soit avec vous !!!', '2015-11-20 22:03:37.851', 3, 5, 3);