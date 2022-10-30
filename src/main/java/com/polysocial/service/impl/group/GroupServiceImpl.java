package com.polysocial.service.impl.group;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.polysocial.consts.GroupAPI;
import com.polysocial.consts.PostAPI;
import com.polysocial.dto.GroupDTO;
import com.polysocial.dto.ListMembersDTO;
import com.polysocial.dto.PageObject;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.MemberDTO;
import com.polysocial.entity.Groups;
import com.polysocial.entity.Members;
import com.polysocial.entity.Users;
import com.polysocial.service.group.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PageObject<GroupDTO> getAll(Integer page, Integer limit) {
        try {
            String url = GroupAPI.API_GET_ALL_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("page",page)
                    .queryParam("limit", limit) 
                         .build();
                
           
            ResponseEntity<PageObject> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, PageObject.class);
            PageObject<GroupDTO> list = entity.getBody();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GroupDTO getOne(Long id) {
        try {
            String url = GroupAPI.API_GET_ONE_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId",id)
                         .build();
                
           
            ResponseEntity<GroupDTO> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, GroupDTO.class);
            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteMemberToGroup(Long groupId, Long userId) {
        try {
            String url = GroupAPI.API_REMOVE_STUDENT;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId",groupId)
                    .queryParam("userId", userId)
                         .build();
                
            ResponseEntity<GroupDTO> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, null, GroupDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGroup(Long groupId) {
        try {
            String url = GroupAPI.API_DELETE_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId",groupId)
                         .build();
            ResponseEntity<GroupDTO> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, null, GroupDTO.class);
            entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public Object getTeacherFromGroup(Long groupId) {
        try {
            String url = GroupAPI.API_GET_TEACHER;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId",groupId)
                         .build();
            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, Object.class);
            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer getUserId(String email) {
       return null;
    }

    @Override
    public Groups createGroup(Groups group) {
        try {
            String url = GroupAPI.API_CREATE_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("group",group)
                         .build();
            ResponseEntity<Groups> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, null, Groups.class);
            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Users getOneMemberInGroup(String email, Long groupId) {
        try {
            String url = GroupAPI.API_GET_ONE_STUDENT;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("email",email)
                    .queryParam("groupId",groupId)
                         .build();
            ResponseEntity<Users> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, Users.class);
            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getMemberInGroup(Long id) {
        try {
            String url = GroupAPI.API_GET_MEMBER_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId",id)
                         .build();
            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, Object.class);
            return  (List<Object>) entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Users getUserById(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createExcel(MultipartFile file) throws IOException {
        try {
            String url = GroupAPI.API_CREATE_GROUP_EXCEL;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("file",file)
                         .build();
            ResponseEntity<Groups> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, null, Groups.class);
//            entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public List<GroupDTO> findByKeywork(String keywork) {
        try {
            String url = GroupAPI.API_FIND_GROUP_BY_KEYWORK;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("keywork",keywork)
                         .build();
            ResponseEntity<GroupDTO> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, GroupDTO.class);
            List<GroupDTO> list = (List<GroupDTO>) entity.getBody();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MemberDTO saveMember(Long userId, Long groupId) {
        try {
            String url = GroupAPI.API_CREATE_MEMBER;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("userId",userId)
                    .queryParam("groupId",groupId)
                         .build();
            ResponseEntity<MemberDTO> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, null, MemberDTO.class);
            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Groups updateGroup(Groups group) {
        try {
            String url = GroupAPI.API_UPDATE_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("group",group)
                         .build();
            ResponseEntity<Groups> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, null, Groups.class);
            return entity.getBody();
        } catch (Exception e) { 
            e.printStackTrace();
            return null;
        }
    }



}
