package com.member;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.member.main.entity.TeamMember;
import com.member.main.exception.InvalidUserId;
import com.member.main.service.TeamMemberService;



@SpringBootTest
class TeamMemberServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(TeamMemberServiceImplTest.class);

    @Autowired
    private TeamMemberService teamMemberService;

    @Test
    public void testAddTeamMember() throws Exception {
        logger.info("Inside testAddTeamMember");

        // Create a new team member
        TeamMember teamMember = new TeamMember();
        teamMember.setFirstName("John Doe");
        teamMember.setLastName("Doe");

        // Call the addTeamMember method
        TeamMember addedMember = teamMemberService.addTeamMember(teamMember);

        // Assert that the returned team member is not null
        assertNotNull(addedMember);
        // Assert that the returned team member has the correct name
      //  assertEquals("John Doe", addedMember.getFirstName());
      //  assertEquals("Doe", addedMember.getLastName());
    }

    @Test
    public void testRegisterTeamMember() throws Exception {
        // Create a new team member
        TeamMember teamMember = new TeamMember();
        teamMember.setFirstName("Jane Smith");
        teamMember.setLastName("Smith");
        teamMember.setEmail("jane@example.com");
        teamMember.setPassword("js123");

        // Call the registerTeamMember method and handle exceptions
        TeamMember registeredMember = teamMemberService.registerTeamMember(teamMember);

        // Assert that the returned team member is not null
        assertNotNull(registeredMember);

        // Validate the attributes of the returned team member
        assertEquals("Jane Smith", registeredMember.getFirstName());
        assertEquals("Smith", registeredMember.getLastName());
        assertEquals("jane@example.com", registeredMember.getEmail());
        assertEquals("js123", registeredMember.getPassword());
    }


    @Test
    public void testGetAllTeamMembers() {
        logger.info("Inside testGetAllTeamMembers");

        // Call the getAllTeamMember method
        List<TeamMember> allMembers = teamMemberService.getAllTeamMember();

        assertNotNull(allMembers);
        assertFalse(allMembers.isEmpty());
    }

    @Test
    public void testGetTeamMemberById() throws InvalidUserId {
        logger.info("Inside testGetTeamMemberById");

        // Create a valid team member ID
        Integer memberId = 1;

        // Create a new team member and add it to the repository
        TeamMember teamMember = new TeamMember();
        teamMember.settId(memberId);
        teamMember.setFirstName("John");
        teamMember.setLastName("Doe");
        // ... Set other properties as needed and save the team member

        // Call the getTeamMemberById method
        TeamMember member = teamMemberService.getTeamMemberById(memberId);

       // assertNotNull(member, "Team member should not be null");
       // assertEquals(member.gettId(), memberId);
    }

    @Test
    public void testUpdateTeamMember() throws InvalidUserId {
        logger.info("Inside testUpdateTeamMember");

        // Create a team member with valid ID and updated name
        TeamMember teamMember = new TeamMember();
        teamMember.settId(1);
        teamMember.setFirstName("Jane Smith");
        teamMember.setLastName("Smith");
        teamMember.setEmail("jane@example.com");
        teamMember.setPassword("js123");

        // Call the updateTeamMember method
     /*  TeamMember updatedMember = teamMemberService.updateTeamMember(teamMember);

        assertNotNull(updatedMember);
        assertEquals(1, updatedMember.gettId());
        assertEquals("Jane Smith", updatedMember.getFirstName());
        assertEquals("Smith", updatedMember.getLastName());
        assertEquals("jane@example.com", updatedMember.getEmail());
        assertEquals("js123", updatedMember.getPassword());*/
    }

    @Test
    public void testDeleteTeamMember() throws InvalidUserId {    
        logger.info("Inside testDeleteTeamMember"); 
 
        // Create a team member with a valid ID
        int memberId = 1;

        try {
            // Delete the team member
            teamMemberService.delete(memberId);

            // Attempt to retrieve the deleted team member should throw InvalidUserId exception
            assertThrows(InvalidUserId.class, () -> teamMemberService.getTeamMemberById(memberId));
        } catch (InvalidUserId e) {
           // fail("InvalidUserId exception should be thrown");
        }
    }
}
