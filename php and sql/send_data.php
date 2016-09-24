<?php 

    #Ensure that the client has provided a value for "FirstNameToSearch" 
    if (isset($_POST["Userid"]) && isset($_POST["loc_to"]) && 
		isset($_POST["loc_from"]) && isset($_POST["vehicle_type"]) && isset($_POST["purpose"]) &&
		isset($_POST["drop_time"]) && isset($_POST["drop_date"])){
         
        #Setup variables 
        $userid = $_POST["Userid"];	
		$loc_to = $_POST["loc_to"];
		$loc_from = $_POST["loc_from"];
		$vehicle_type = $_POST["vehicle_type"];
		$purpose = $_POST["purpose"];
		$drop_time = $_POST["drop_time"];
		$drop_date = $_POST["drop_date"];
		
		
		/*$userid = '36446';	
		$loc_to = 'sector22';
		$loc_from = 'office';
		$purpose = 'working late';
		$vehicle_type = 'nonac';
		$drop_time = '21:00:00';
		$drop_date = '2014-12-10';*/
		
        #Connect to Database 
        $con = mysqli_connect("localhost","root","", "travel"); 
         
        #Check connection 
        if (mysqli_connect_errno()) { 
            echo 'Database connection error: ' . mysqli_connect_error(); 
            exit(); 
        } 

        #Escape special characters to avoid SQL injection attacks 
        $userid = mysqli_real_escape_string($con, $userid); 
         
        #Query the database to get the user details. 
        $username = mysqli_query($con, "select username from users where id = '$userid'"); 

        #If no data was returned, check for any SQL errors 
        if (!$username) { 
            echo 'Could not run query: ' . mysqli_error($con); 
            exit; 
        } 
		
		$num_rows = mysqli_num_rows($username);
		if($num_rows) {
		    $row = mysqli_fetch_row($username); 
			$name = $row[0];
			//echo $name;
		    $insert_vals = mysqli_query($con, "insert into booking(id, name, loc_to, loc_from, purpose,vehicle_type, drop_time, drop_date) values('$userid', '$name', '$loc_to', '$loc_from','$purpose','$vehicle_type','$drop_time', '$drop_date')
");
            if(!$insert_vals) {
			    echo 'Could not run query: ' . mysqli_error($con); 
                exit; 
			}
			$result_data["success"] = 1;
			echo json_encode($result_data);  
		} 
		
        #Output the JSON data 
        
    }else{ 
        echo "Could not complete query. Missing parameter";  
    }
?>