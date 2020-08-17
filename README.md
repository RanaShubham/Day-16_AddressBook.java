# Day-6_AddressBook.java


1.Sorting by last name may give buggy results if second name of two users is same. Therefore sorting here works as intended only when every user has diffrent last name.
Hence it would have been much better if sorting was to be done by some unique ID.
2. Sorting behaviour is based on default Arrays.sort(). ThereFore it may not show expected results. For ex: In last names "Singh" vs "bahadur" when sorted in ascening
order,Singh will come first becasue Arrays.sort() sorts lexographically and thus capital letters(S) come before small letter(b).
3. When prompted, enter file name along with extension.
4. All the files are created in current working directory of java only.
5. All the sorting has been done in ascending order only.

These features I have not implemented yet-
1. breaking ties using first name when sorting records.
2. I didnt implement the feature to edit an entry explicitly however I have added feature to delete record. Using that its possible to make an edit indirectly by
 deleteing a record and then entring it again with new edits.

NOTE: While entering each fields for each user, DO NOT USE SPACE(/s)
If you must add more that one words in a feild use _ or - instead of space
FOR EX: While entering Address field, write "Agra_Delhi" instead of "Agra Delhi"
