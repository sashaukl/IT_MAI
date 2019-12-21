<?php
$servername = $_ENV["DB_HOST"]; //"localhost";
$username = $_ENV[""];
$password = $_ENV[""];
$dbname = $_ENV["DB_NAME"];

//probably usless
$db_port = $_ENV["DB_PORT"];
$app_port = $_ENV["APP_PORT"];
$app_host = $_ENV["APP_HOST"];

if(isset($_POST)){
	
	//get number from json
	$json_array = json_decode( $_POST[0]);
	$num = $json_array->properties->number->minimum;	
	$conn = new mysqli($servername, $username, $password, $dbname);
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
		
	//check if table exisxts
	$val = $conn->query('select 1 from reqests_numbers LIMIT 1');
	if($val == FALSE){
		$conn->query("CREATE TABLE reqests_numbers (digits int(38) NOT NULL, UNIQUE KEY digits (digits)) ENGINE=MyISAM DEFAULT CHARSET=utf8");
	}
	
	$sql = "SELECT * FROM reqests_numbers WHERE digits=".($num-1);
	$result = $conn->query($sql);
	if ($result->num_rows > 0) {
		// output data of each row
		while($row = $result->fetch_assoc()) {
			$json_response = (object) array("error" => "Someone entered number reduced by one val = $num", "type"=>2 );
			error_log(json_encode($json_response), 3, "err_log.log");
			die(json_encode($json_response));
		}
	}	
	$sql = "INSERT INTO reqests_numbers (digits) VALUES (".$num.")";	
	$result = $conn->query($sql);
	if ($result == null){
		$json_response = (object) array("error" => "Number alredy exists = $num", "type"=>1 );
		error_log(json_encode($json_response), 3, "err_log.log");
		die(json_encode($json_response));
	}
	
	$json_response = (object) array('result' => $num+1);
	echo json_encode($json_response);

	$conn->close();
}

?>
