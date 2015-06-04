<?php
	require 'lib\Slim\Slim.php';
	\Slim\Slim::registerAutoloader();
	
	$app = new \Slim\Slim();
	
	function userExists($userId) {
		require 'db.php';
		
		$query = $db->prepare('SELECT * FROM User WHERE userId = ? LIMIT 1');
		$query->execute(array($userId));
		return !empty($query->fetch());
	}
	
	function pkmnExists($pkmnId) {
		require 'db.php';
		
		$query = $db->prepare('SELECT * FROM Pokemon WHERE pkmnId = ? LIMIT 1');
		$query->execute(array($pkmnId));
		return !empty($query->fetch());
	}
	
	function moveExists($moveId) {
		require 'db.php';
		
		$query = $db->prepare('SELECT * FROM Move WHERE moveId = ? LIMIT 1');
		$query->execute(array($moveId));
		return !empty($query->fetch());
	}
	
	/* Registar utilizador */
	$app->post('/api/users', function() {
		if (!isset($_POST['username']) || !isset($_POST['password'])) {
			header('HTTP/1.0 404 Not Found');
			die();
		}
		
		require 'db.php';
		
		$password = password_hash($_POST['password'], PASSWORD_DEFAULT);
		
		$query = $db->prepare('INSERT INTO User (username, password) VALUES (?, ?)');
		$query->execute(array($_POST['username'], $password));
	});
	
	/* Obter os Pokémon de um utilizador */
	$app->get('/api/users/:userId/pokemon', function($userId) {
		if (!userExists($userId) || !pkmnExists($_POST['pkmnId'])) {
			header('HTTP/1.0 404 Not Found');
			die();
		}
		
		require 'db.php';
		
		$query = $db->prepare('SELECT pkmnId FROM UserPokemon WHERE userId = ?');
		$query->execute(array($userId));
		$userPkmn = $query->fetchAll();

		echo json_encode($userPkmn);
	});
	
	/* Adicionar um Pokémon a um utilizador */
	$app->post('/api/users/:userId/pokemon', function($userId) {
		if (!isset($_POST['pkmnId']) || !userExists($userId) || !pkmnExists($_POST['pkmnId'])) {
			header('HTTP/1.0 404 Not Found');
			die();
		}
		
		require 'db.php';
		
		$query = $db->prepare('SELECT COUNT(*) FROM UserPokemon WHERE userId = ?');
		$query->execute(array($userId));
		$userPkmnCount = $query->fetch();
		
		if ($userPkmnCount === 6) { // Se o limite de Pokémon por utilizador foi atingido
			header('HTTP/1.0 404 Not Found');
			die();
		}
		
		$query = $db->prepare('INSERT INTO UserPokemon (userId, pkmnId) VALUES (?, ?)');
		$query->execute(array($userId, $_POST['pkmnId']));
	});
	
	/* Remover um Pokémon de um utilizador */
	$app->delete('/api/users/:userId/pokemon/:pkmnId', function($userId, $pkmnId) {
		if (!userExists($userId) || !pkmnExists($pkmnId)) {
			header('HTTP/1.0 404 Not Found');
			die();
		}
		
		require 'db.php';
		
		$query = $db->prepare('DELETE FROM UserPokemon WHERE userId = ? AND pkmnId = ?');
		$query->execute(array($userId, $pkmnId));
	});
	
	/* Obter um Pokémon */
	$app->get('/api/pokemon/:pkmnId', function($pkmnId) {
		require 'db.php';
		
		$query = $db->prepare('SELECT * FROM Pokemon WHERE pkmnId = ? LIMIT 1');
		$query->execute(array($pkmnId));
		$pokemon = $query->fetchAll();
		
		if (!empty($pokemon)) echo json_encode($pokemon));
		else header('HTTP/1.0 404 Not Found');
	});
	
	/* Adicionar um Pokémon */
	$app->post('/api/pokemon', function($pkmnId) {
		if (!isset($_POST['pkmnId']) || !isset($_POST['name']) || !isset($_POST['sprite']) || !isset($_POST['hp'])) {
			header('HTTP/1.0 404 Not Found');
			die();
		}
		
		require 'db.php';
		
		$query = $db->prepare('INSERT INTO Pokemon (pkmnId, name, sprite) VALUES (?, ?, ?, ?)');
		$query->execute(array($_POST['pkmnId'], $_POST['name'], $_POST['sprite'], $_POST['hp']));
	});
	
	/* Obter as jogadas de um Pokémon */
	$app->get('/api/pokemon/:pkmnId/moves', function($pkmnId) {
		require 'db.php';
		
		if (!pkmnExists($_POST['pkmnId'])) {
			header('HTTP/1.0 404 Not Found');
			die();
		}
		
		$query = $db->prepare('SELECT * FROM PokemonMove WHERE pkmnId = ?');
		$query->execute(array($pkmnId));
		$moves = $query->fetchAll();
		
		if (!empty($moves)) echo json_encode($moves);
		else header('HTTP/1.0 404 Not Found');
	});
	
	/* Adicionar uma jogada a um Pokémon */
	$app->post('/api/pokemon/:pkmnId/moves', function($pkmnId) {		
		if (!isset($_POST['moveId']) || !moveExists($_POST['moveId']) || !pkmnExists($_POST['pkmnId'])) {
			header('HTTP/1.0 404 Not Found');
			die();
		}
		
		require 'db.php';
		
		$query = $db->prepare('INSERT INTO PokemonMove (pkmnId, moveId) VALUES (?, ?)');
		$query->execute(array($pkmnId, $_POST['moveId']));
	});
	
	/* Remover uma jogada de um Pokémon */
	$app->delete('/api/pokemon/:pkmnId/moves/:moveId', function($pkmnId, $moveId) {
		if (!pkmnExists($pkmnId) || !moveExists($moveId) {
			header('HTTP/1.0 404 Not Found');
			die();
		}
		
		require 'db.php';
		
		$query = $db->prepare('DELETE FROM PokemonMove WHERE pkmnId = ? AND moveId = ?');
		$query->execute(array($userId, $pkmnId));
	});
	
	/* Obter uma jogada */
	$app->get('/api/moves/:moveId', function($moveId) {
		require 'db.php';
		
		$query = $db->prepare('SELECT * FROM Move WHERE moveId = ? LIMIT 1');
		$query->execute(array($moveId));
		$move = $query->fetchAll();
		
		if (!empty($move)) echo json_encode($move));
		else header('HTTP/1.0 404 Not Found');
	});
	
	/* Adicionar uma jogada */
	$app->post('/api/moves', function($moveId) {
		if (!isset($_POST['moveId']) || !isset($_POST['name']) || !isset($_POST['power'])) {
			header('HTTP/1.0 404 Not Found');
			die();
		}
		
		require 'db.php';
		
		$query = $db->prepare('INSERT INTO Move (moveId, name, power) VALUES (?, ?, ?)');
		$query->execute(array($_POST['moveId'], $_POST['name'], $_POST['power']));
	});
	
	$app->run();
?>
