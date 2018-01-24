import java.net._
import java.io._

val ss = new ServerSocket(4000) 
val sock = ss.accept //accepts socket/blocking call
val is = sock.getInputStream
val os = sock.getOutputStream
Thread.sleep(100)
val buff = new Array[Byte](is.available) //how many bytes sitting there ready to read
is.read(buff)
println(new String(buff))
val message = """HTTP/1.1 200 OK

Hello this is Austin
"""
os.write(message.getBytes)
sock.close


//Fake a request for google
/*
GET / HTTP/1.1
Host: www.google.com:80


*/

//default port is 80
//blank lines used to separate header information
