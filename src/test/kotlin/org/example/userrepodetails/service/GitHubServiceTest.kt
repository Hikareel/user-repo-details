package org.example.userrepodetails.service

import com.google.gson.Gson
import io.mockk.mockk
import org.example.userrepodetails.entity.Branch
import org.example.userrepodetails.entity.Message
import org.example.userrepodetails.entity.Repository
import org.example.userrepodetails.entity.UserData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GitHubServiceTest{
	@Autowired
	private val gitHubService: GitHubService = mockk()

	@Autowired
	private val gson: Gson = Gson()

	@Test
	@DisplayName("When given existing user name should return proper data")
	fun usernameCorrect(){
		//Given
		val username = "octocat"
		val branches1 = listOf(Branch("master", "b33a9c7c02ad93f621fa38f0e9fc9e867e12fa0e"))
		val repo1 = Repository("git-consortium", branches1)
		val branches2 = listOf(Branch("master", "7e068727fdb347b685b658d2981f8c85f7bf0585"))
		val repo2 = Repository("hello-worId", branches2)
		val branches3 = listOf(
			Branch("master", "7fd1a60b01f91b314f59955a4e4d4e80d8edf11d"),
			Branch("octocat-patch-1", "b1b3f9723831141a31a1a7252a213e216ea76e56"),
			Branch("test", "b3cbd5bbd7e81436d2eee04537ea2b4c0cad4cdf")
		)
		val repo3 = Repository("Hello-World", branches3)
		val branches4 = listOf(
			Branch("gh-pages", "c0e4a095428f36b81f0bd4239d353f71918cbef3"),
			Branch("master", "3a9796cf19902af0f7e677391b340f1ae4128433")
		)
		val repo4 = Repository("octocat.github.io", branches4)
		val branches5 = listOf(
			Branch("change-the-title", "f439fc5710cd87a4025247e8f75901cdadf5333d"),
			Branch("main", "d0dd1f61b33d64e29d8bc1372a94ef6a2fee76a9"),
			Branch("test-branch", "58060701b538587e8b4ab127253e6ed6fbdc53d1")
		)
		val repo5 = Repository("Spoon-Knife", branches5)
		val branches6 = listOf(Branch("gh-pages", "57523742631876181d95bc268e09fb3fd1a4d85e"))
		val repo6 = Repository("test-repo1", branches6)
		val repos = listOf(repo1, repo2, repo3,repo4, repo5, repo6)
		val expectedUserData = gson.toJson(UserData(username, repos))
		//When
		val actualUserData = gitHubService.getUserData(username)
		//Then
		assertEquals(expectedUserData, actualUserData)
	}

	@Test
	@DisplayName("When given nonexistent username should return error message \"User not found\"")
	fun usernameIncorrect(){
		//Given
		val expectedMessage = gson.toJson(Message("404 Code", "User not found"))
		val badUsername = "!@#asdf!@#"
		//When
		val actualMessage = gitHubService.getUserData(badUsername)
		//Then
		assertEquals(expectedMessage, actualMessage)
	}
}