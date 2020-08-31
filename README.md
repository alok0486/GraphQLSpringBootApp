# GraphQLSpringBootApp

# Query to get Person on person ID

  {
	personById(id:"1"){
	email
    	id
        name
        addressId
        address{
       	 	city
        	pin
        }
        dob
   }
}

# Query to get on address ID
addressByPersonId(id:"2"){
  id
  city
  pin
  house_no
}
# Query to geton person ID
addressById(id:"001"){
  id
  city
  pin
  house_no
}
