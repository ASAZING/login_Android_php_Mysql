<?php 
require_once 'connection.php';
session_start();
$email=$_POST['email'];
$pass=$_POST['pass'];


 try {
     if(connect()){
    $query=$conn->prepare("SELECT * FROM users WHERE email=? AND pass=?");
    $query->bind_param('ss',$email,$pass);
    $query->execute();
    $result = $query->get_result();
if ($row = $result->fetch_assoc()) {
         echo json_encode($row,JSON_UNESCAPED_UNICODE);     
}
$query->close();
$conn->close();
     }else {
         echo "No se puede conectar";
     }
    
 } catch (\Throwable $th) {
     echo "error".$th;
 }

?>
