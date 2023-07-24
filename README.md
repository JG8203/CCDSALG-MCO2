# CCSDSALG (Data Structures and Algorithms): Major Course Output 2 - Graphs

This is the second major course output for the course "Data Structures and Algorithms". This project deals with a social graph dataset collected from Facebook and entails implementing a program that provides functionalities to process the data.

## Deadline

- July 31, 2023 (Monday), 8:00 AM

## Weightage

- This project contributes to 20% of your final grade

## Submission Guidelines

- Submit all deliverables in AnimoSpace
- Filename format: CCDSALG-MCO2-Section-GroupNumber.zip

## Deliverables Checklist

- [x] Source.zip file containing a folder called `source`, which includes all source codes for the project
- [x] A PDF file named `report.pdf`, which contains the project report

## Project Specifications

The project consists of different stages:

1. Loading the Graph: Read the file and load the graph into an appropriate data structure for processing.
2. Display Friend List: Given an ID number, display that person’s friend list.
3. Display Connections: Given two ID numbers, display the connection between those two people in the network, if it exists.

## Writing the Report

The report should include:

1. An introduction to the project and an outline of the report content
2. A description of the data structure used in representing the social graph data, including the rationale behind the use of such data structure/s
3. A description of the algorithms used to display the friend list and the connection between two people in the network
4. An algorithmic analysis of the algorithms in terms of time complexity
5. Summary of findings and group's learnings, insights, and realizations

## Working with Groupmates

- Collaboration should be done with a genuine concern for the outcome of the project
- Each member of the group should have a substantial understanding of the submitted work

## Example Execution

```plaintext
Input file path: mysamplefile.txt
Graph loaded!
MAIN MENU
[1] Get friend list
[2] Get connection
[3] Exit
Enter your choice: 1
Enter ID of person: 3
Person 3 has 5 friends!
List of friends: 16 726 740 1744 2564
MAIN MENU
[1] Get friend list
[2] Get connection
[3] Exit
Enter your choice: 1
Enter ID of person: 740
Person 3 has 20 friends!
List of friends: 2 3 14 22 50 80 111 122 140 150 199 600 783 956 1035 1833 1834 1835 1990 1993
MAIN MENU
[1] Get friend list
[2] Get connection
[3] Exit
Enter your choice: 2
Enter ID of first person: 1111
Enter ID of second person: 1993
There is a connection from 1111 to 1993!
1111 is friends with 3
3 is friends with 740
740 is friends with 1993
MAIN MENU
[1] Get friend list
[2] Get connection
[3] Exit
Enter your choice: 2
Enter ID of first person: 2204
Enter ID of second person: 2205
Cannot find a connection between 2204 and 2205
MAIN MENU
[1] Get friend list
[2] Get connection
[3] Exit
Enter your choice: 3
```

## Please Note

- Complete all the tasks listed above
- Always ensure your group collaboration is effective and balanced
- Always adhere to the Academic Honesty Policy

Good luck!

## Project Checklist
### Software Development 

#### Part 1: Loading the Graph
- [x] Write a function to read the file containing the social network data.
- [x] Ensure the function can accept as input the file name or file path.
- [x] Write a function to convert the data into a graph representation.
- [x] Make sure the function is capable of handling up to 5000 accounts in a single network.
- [x] Test loading the graph with example data (Caltech36, Rice31, and Trinity100).
- [x] Implement a menu for user interaction.

#### Part 2: Display Friend List
- [x] Write a function that accepts an ID and displays the corresponding friend list.
- [x] Ensure the function displays an error message if the ID doesn't exist.
- [x] Test the function with different ID inputs.

#### Part 3: Display Connections
- [x] Write a function that accepts two IDs and displays the connection between them.
- [x] Ensure the function displays an error message if one or both IDs don't exist.
- [x] Test the function with different pairs of ID inputs.

### Report Writing

- [ ] Write an introduction and outline of the report content.
- [ ] Describe the data structure used in detail and provide the rationale for its use.
- [ ] Describe in detail the algorithms used to:
  - [ ] Load the data into a graph
  - [ ] Display a person's friend list
  - [ ] Display the connection between two people
- [ ] Analyze and write about the time complexity of the implemented algorithms.
- [ ] Summarize findings, learnings, insights, and realizations in a concluding section.
- [ ] Add a section detailing the group members and their contributions.
- [ ] Ensure APA format for references if any.

### Finalization

- [ ] Review the source code for potential improvements.
- [ ] Re-read and proofread the report.
- [ ] Prepare the source.zip file containing all the source codes.
- [ ] Prepare the report.pdf file.

### Submission

- [ ] Submit both the source.zip and report.pdf files on AnimoSpace.
- [ ] Ensure the files are named in the correct format: CCDSALG-MCO2-Section-GroupNumber.zip
- [ ] Submit before the deadline: July 31, 2023, 08:00 AM.
