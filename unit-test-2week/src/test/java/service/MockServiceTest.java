package service;

import domain.Champion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.MockRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockServiceTest {

    @Mock
    private MockRepository mockRepository;

    @InjectMocks
    private MockService mockService;

    // ******************************************
    // 기본 mock test method 연습
    // ******************************************

    @Test
    public void 챔피언이름을가져오면_무조건_카이사를_리턴한다() {
//        Champion champion = mock(Champion.class);

        Champion champion = mock(Champion.class);
        when(champion.getName()).thenReturn("카이사");
        assertThat(champion.getName(), is("카이사"));
        System.out.println("Champio"+champion.getName());

        //champion.getName()을 호출하면 "카이사"를 리턴한다.
        //when(champion.getName()).thenReturn("카이사");
        //assertThat(champion.getName(), is("카이사"));
    }

    // 1. when, thenReturn을 사용하여 어떠한 챔피언 이름을 입력해도 베인을 리턴하도록 테스트하세요
    @Test
    public void 무조건_베인_리턴(){
        Champion champion = mock(Champion.class);
        champion.setName("시비르");
        champion.setPosition("바텀");

        when(champion.getName()).thenReturn("베인");
        assertThat(champion.getName(),is("베인"));
    }

    // 2. 챔피언 이름으로 야스오를 저장하면, doThrow를 사용하여 Exception이 발생하도록 테스트 하세요.

    @Test(expected = Exception.class)
    public void 야스오_ExceptionControll(){
        Champion champion = mock(Champion.class);

        doThrow(new Exception()).when(champion).setName("야스오");
        champion.setName("야스오");
        champion.setPosition("미드");
    }


    // 3. verify 를 사용하여 '미드' 포지션을 저장하는 프로세스가 진행되었는지 테스트 하세요.

    @Test
    public void SetMidPosition(){
        Champion champion = mock(Champion.class);
        champion.setName("야스오");
        champion.setPosition("미드");
        champion.setHasSkinCount(7);
        verify(champion).setPosition("미드");
    }

    // 4. champion 객체의 크기를 검증하는 로직이 1번 실행되었는지 테스트 하세요.
    @Test
    public void chmpion_객체크기(){
        ArrayList<Champion> champions = mock(ArrayList.class);
        Champion champion = mock(Champion.class);
        champion.setName("야스오");
        champion.setPosition("미드");

        champions.add(champion);

        int Size = champions.size();

        verify(champions, times(1)).size();
    }


    // 4-1. champion 객체에서 이름을 가져오는 로직이 2번 이상 실행되면 Pass 하는 로직을 작성하세요.

    @Test
    public void getName_두번_호출(){
        Champion champion = mock(Champion.class);
        champion.setName("에코");
        champion.setPosition("미드");
        champion.setHasSkinCount(3);
        champion.getName();
        champion.getName();
        verify(champion,atLeast(2)).getName();

    }


    // 4-2. champion 객체에서 이름을 가져오는 로직이 최대 3번 이하 실행되면 Pass 하는 로직을 작성하세요.

    @Test
    public void getName_최소_3번(){
        Champion champion = mock(Champion.class);
        champion.setName("에코");
        champion.setPosition("미드");
        champion.setHasSkinCount(3);
        champion.getName();
        champion.getName();
        champion.getName();
        verify(champion, atMost(3)).getName();
    }

    // 4-3. champion 객체에서 이름을 저장하는 로직이 실행되지 않았으면 Pass 하는 로직을 작성하세요.

    @Test
    public void 저장X(){
        Champion champion = mock(Champion.class);
        //champion.setName("에코");
        champion.setPosition("미드");
        champion.setHasSkinCount(3);

        verify(champion,never()).setName(any(String.class)); //anyString()
    }

    // 4-4. champion 객체에서 이름을 가져오는 로직이 200ms 시간 이내에 1번 실행되었는 지 검증하는 로직을 작성하세요.

    @Test
    public void shouldOneTimeInvocationIn200msForChampionGetName(){
        Champion champion = mock(Champion.class);
        champion.setName("트페");
        champion.setPosition("미드");
        champion.setHasSkinCount(8);

        System.out.println("챔피언 : "+champion.getName());
        verify(champion,timeout(200)).getName();
    }


    // ******************************************
    // injectmock test 연습
    // ******************************************

    @Test
    public void 챔피언정보들을Mocking하고Service메소드호출테스트() {
 //       when(mockService.findByName(anyString())).thenReturn(new Champion("루시안", "바텀", 5));
   //     String championName = mockService.findByName("애쉬").getName();
     //   assertThat(championName, is("루시안"));
       // verify(mockRepository, times(1)).findByName(anyString());
        when(mockService.findByName(anyString())).thenReturn(new Champion("루시안", "바텀", 5));
        String championName = mockService.findByName("애쉬").getName();
        assertThat(championName, is("루시안"));
        verify(mockRepository, times(1)).findByName(anyString());
    }

    // 1. 리산드라라는 챔피언 이름으로 검색하면 미드라는 포지션과 함께 가짜 객체를 리턴받고, 포지션이 미드가 맞는지를 테스트하세요

    @Test
    public void shouldPosiotionTopForFindBynameLissandra(){
        when(mockService.findByName("리산드라")).thenReturn(new Champion("리산드라","미드",5));
        assertThat(mockService.findByName("리산드라").getPosition(),is("미드"));

        assertThat(mockService.findByName("리산드라").getName(),is("리산드라"));
    }

    // 2. 2개 이상의 챔피언을 List로 만들어 전체 챔피언을 가져오는 메소드 호출시 그 갯수가 맞는지 확인하는 테스트 코드를 작성하세요.


    //mock로 구현해서 null?
    @Test
    public void shouldNumberRightOfList_mock(){
        List<Champion> champions = mock(List.class);
        Champion champion1 = mock(Champion.class);
        Champion champion2 = mock(Champion.class);
        champion1.setName("루시안");
        champion2.setName("애쉬");
        champions.add(champion1);
        champions.add(champion2);

        System.out.println(champions.get(0));
        assertThat(champions.size(),is(0));
    }


    //champions만 mock일 때
    @Test
    public void TestOnlyChampionsMock(){
        List<Champion> champions = mock(List.class);
        champions.add(new Champion("루시안","바텀",5));
        champions.add(new Champion("유미","서폿",3));
        champions.add(new Champion("애쉬","바텀",6));


        when(mockService.findAllChampions()).thenReturn(champions);
        assertThat(mockService.findAllChampions().size(),is(0));
        System.out.println(champions.get(0));
        assertThat(champions.size(),is(0));
    }

    //chmpion만 mock일 때
    @Test
    public void TestOnlyChampionMock(){
        List<Champion> champions = new ArrayList<>();
        Champion champion1 = mock(Champion.class);
        Champion champion2 = mock(Champion.class);
        champion1.setName("루시안");
        champion2.setName("애쉬");
        champions.add(champion1);
        champions.add(champion2);

        when(mockService.findAllChampions()).thenReturn(champions);
        assertThat(mockService.findAllChampions().size(),is(2));
        System.out.println(champions.get(0));
        System.out.println(mockService.findAllChampions().get(0));
        //assertThat(champions.size(),is(0));
    }

    @Test
    public void shouldNumberRightOfList(){
        List<Champion> champions = new ArrayList<>();
        champions.add(new Champion("루시안","바텀",5));
        champions.add(new Champion("유미","서폿",3));
        champions.add(new Champion("애쉬","바텀",6));

        when(mockService.findAllChampions()).thenReturn(champions);
        assertThat(mockService.findAllChampions().size(),is(3));
        verify(mockRepository,times(1)).findAll();
    }

    // 3. 챔피언을 검색하면 가짜 챔피언 객체를 리턴하고, mockRepository의 해당 메소드가 1번 호출되었는지를 검증하고, 그 객체의 스킨 개수가
    //    맞는지 확인하는 테스트코드를 작성하세요.

    @Test
    public void testSkinCount(){
        when(mockService.findByName(anyString())).thenReturn(new Champion("오공","정글",3));
        assertThat(mockService.findByName("동재").getHasSkinCount(),is(3));
        verify(mockRepository,times(1)).findByName(anyString());
    }

    // 4. 2개 이상의 가짜 챔피언 객체를 List로 만들어 리턴하고, 하나씩 해당 객체를 검색한 뒤 검색을 위해 호출한 횟수를 검증하세요.
    @Test
    public void testInvocationCoungWhenGetChampionList(){
        List<Champion> champions = new ArrayList<>();
        champions.add(new Champion("루시안","바텀",5));
        champions.add(new Champion("유미","서폿",3));
        champions.add(new Champion("애쉬","바텀",6));

        when(mockService.findAllChampions()).thenReturn(champions);
        assertThat(mockService.findAllChampions().get(0).getName(),is("루시안"));
        assertThat(mockService.findAllChampions().size(),is(3));
        verify(mockRepository,times(2)).findAll();
    }


    //가장 많이 사용되는 테스트 중 하나로 BDD 방식에 기반한 테스트 방법 예제
    @Test
    public void 탐켄치를_호출하면_탐켄치정보를_리턴하고_1번이하로_호출되었는지_검증() {
        //given
        given(mockRepository.findByName("탐켄치")).willReturn(new Champion("탐켄치", "서폿", 4));
        //when
        Champion champion = mockService.findByName("탐켄치");
        //then
        verify(mockRepository, atLeast(1)).findByName(anyString());
        assertThat(champion.getName(), is("탐켄치"));
    }
}