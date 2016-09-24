<?php 

    #Ensure that the client has provided a value for "FirstNameToSearch" 
    if (isset($_POST["Userid"]) ){ 
         
        #Setup variables 
        $userid = $_POST["Userid"];
		#$userid = '36446';
		$response = array();
        
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
        $userdetails = mysqli_query($con, "SELECT loc_to,loc_from,purpose,drop_time, drop_date FROM `booking` WHERE id = '$userid'"); 

        #If no data was returned, check for any SQL errors 
        if (!$userdetails) { 
            echo 'Could not run query: ' . mysqli_error($con); 
            exit; 
        } 
		
		$num_rows = mysqli_num_rows($userdetails);
		if($num_rows) {
		    $i = 0;
		    while ($row = mysqli_fetch_assoc($userdetails)) {
				$response[$i]["loc_to"] = $row["loc_to"];
				$response[$i]["loc_from"] = $row["loc_from"];
				$response[$i]["purpose"] = $row["purpose"];
				$response[$i]["drop_time"] = $row["drop_time"];
				$response[$i]["drop_date"] = $row["drop_date"];
				
				$i = $i+1;
			}
		} 
		
        #Output the JSON data 
        echo json_encode($response);  
    }else{ 
        echo "Could not complete query. Missing parameter";  
    } 
?>