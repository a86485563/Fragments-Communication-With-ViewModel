# Fragments Communication With ViewModel

- [Fragments Communication With ViewModel](#fragments-communication-with-viewmodel)
  - [前言](#前言)
  - [關於本篇](#關於本篇)
  - [了解重點](#了解重點)
  - [Fragment](#fragment)
  - [What is Fragment?](#what-is-fragment)
  - [How to use Fragment?](#how-to-use-fragment)
    - [step 1 畫 Layout](#step-1-畫-layout)
    - [step 2 完成 layout 對應的邏輯。](#step-2-完成-layout-對應的邏輯)
    - [step 3 綁上宿主(host)](#step-3-綁上宿主(host))
  - [ViewModel](#viewmodel)
- [ref](#ref)

## 前言

有一天小明坐在樹下思考，網頁有 MVC 的開發模式，vue 有葡萄圖的元件為單位的開發原則，那 app 呢?

Hi ya ~ 總不能所有的東西所有的東西都在 Activity 一路洋洋灑灑打到底八!?於是科學最有趣的就是模仿，他學了 mvc 有了 mvp 最後演變成大家都在討論的"mvvm" 。
`model`-`view`-`view model`的開發方式。

故事扯太遠了，今天沒有要介紹`MVVM` ~~想知道的話就，按讚訂閱加分享，有機會再跟大家分享~~，一步一步來，從`view model` 簡單的用法走起。

## 關於本篇

大家好，台嘎厚!

此篇想要展示使用兩個`Fragment`將我們可愛的 Activity 對切，分成兩個區塊，上半部顯示球員清單，下半部顯示該點擊的球員資料喔，對了湖人一輪游，害我輸兩千，歐郎賣造~

先附上成品的圖片。

<img src="https://github.com/a86485563/Fragments-Communication-With-ViewModel/blob/master/Screenshot_1625233915.png" alt="demoImage" width="200"/>


## 了解重點

簡單的介紹`fragment、view model`。

每個 Activity、Fragment 都有自己的 Livecycle，那如何在此之間逍遙的遊走傳值，小弟使用的是`view model` ，當然還有其他方式喔，~~期待 james 分享~~。

## Fragment

一隻 app 打開的時候，第一個執行的是`Main Activity`，若粗魯的以畫面來區分 Activity。

那問題來了，我希望有一隻搜尋的 App，上半部呈現列表關鍵字並且可以點擊，下半部是 list 呈現所點擊的詳細資料，那有幾個 Activity?

黑丟! 就是一個! 利用 `佛先生，瑞格門(Mr.fragment)`幫助我們將 UI 切割成區塊，並且模組化。Hi ya 好處多多自己上網查，延伸閱讀。

- [Reasons to use Android Single-Activity Architecture with Navigation Component](https://oozou.com/blog/reasons-to-use-android-single-activity-architecture-with-navigation-component-36)。
- [Single activity: Why, when, and how (Android Dev Summit '18)](https://www.youtube.com/watch?v=2k8x8V77CrU)

## What is Fragment?

打開神奇寶貝圖鑑得知，有點類似 Activity，同樣擁有自己的 xml 檔，每個 fragment 有自己的 live cycle，去看文件的話，官網會用`UI controllers`稱呼 fragment 和 activity。

特點:

- 幫助螢幕畫面分割成區，使功能模組化，可以重複使用。
- 有自己的 Lifecycle。
- `不能夠自己一個生存，要依附在Activity上，或是其他fragment`。

## How to use Fragment?

1. 畫 Layout
2. 完成 layout 對應的邏輯。
3. `綁上宿主(host)`

> 舉我的 demo 為例。
> 我有兩個 Fragment，都 host 在 mainActivity 上，這邊把 mainActivity 當作一個`容器`，`透過這個容器來幫忙傳遞viewmodel`

### step 1 畫 Layout

> 1.  palyers:
>     > 目的:顯示球員列表。
>     >
>     > layout:List。
> 2.  player_details :
>     > 目的:顯示球員詳細資訊。
>     >
>     > layout:
>     > [textview(用來顯示項目)+textview(顯示變動的資料) ]\*4

### step 2 完成 layout 對應的邏輯。

詳細參考[Fragment Lifecycle](https://developer.android.com/reference/android/app/Fragment) 該做什麼。

在 onCreateView 的階段將 xml 元件和邏輯綁定。

`view model相關設定在onActivityCreated後做` 有原因，因為 onActivityCreated 是對應 Activity 完成`created()階段`後才執行，因此在`onActivityCreated`後便做保證了我的 Activity 執行了，才能夠透過他幫我傳遞`viewmodel`。

### step 3 綁上宿主(host)

回到 MainActivity 上透過 fragmentManager 綁上~

```kotlin
  supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<PlayersListFragment>(R.id.fragment_container_playerList)
            add<PlayerDetailsFragment>(R.id.fragment_container_playerDetail)
        }
```

do re mi so 完成了。

## [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

官網大致再說，希望能將 Activity、Fragment (這類的 UI controllers)，能夠更專注在與使用者的互動、呈現 UI、掌管功能面的東西(operating system communication)。`將資料丟給view model 儲存`。

優點:

- 面免因為 activity 或是 fragment，destroy or re-create 了，使資料消失發生的問題。
- 透過 view model 可以存較大的資料如圖檔。

使用方式:

view model 裏頭有個`livedata`，他就是我們的主角，`也就是要改變的對象`。
那在不同的 fragment 中，`有人扮演更改livedata的腳色，有人扮演監聽livedata的腳色`，來完成這一套組合技。

舉我的當飯粒:

`PlayersListFragment` 的 list 被 click -> 改變 viewmodel 的`selectedPlayer` -> `PlayerDetailsFragment`監聽到改變了->取 playerDetail -> 改變 UI。

<img src="https://github.com/a86485563/Fragments-Communication-With-ViewModel/blob/master/viewmodel.png" alt="flowImage" width="600"/>



```kotlin
class PlayerViewModel : ViewModel() {
    val selectedPlayer = MutableLiveData<String>() //要改變的對象。
    val repository = PlayersRepository() //資料操作的物件。

    fun 改變選擇的球員(player: String){
        //to do
    }

    fun 取的球員的詳細資料(){
        repository.getDetail(selectedPlayer)
    }
}
```

`改變者: PlayersListFragment`

```kotlin

 override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //取的view model
        viewModel = ViewModelProvider(requireActivity()).get(PlayerViewModel::class.java)

       ....

        //click後 改變view model 被監視參數的值
        lv.onItemClickListener =
            AdapterView.OnItemClickListener { adapter: AdapterView<*>?, itemView: View, pos: Int, id: Long ->
                val tv = itemView as TextView
                Toast.makeText(this.context, tv.text.toString(), Toast.LENGTH_SHORT).show()
                if(viewModel.selectedPlayer.value != tv.text.toString()){
                    viewModel.selectPlayer(tv.text.toString())
                }
            }
    }
```

`監聽者 : PlayerDetailsFragment `

```kotlin
  override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity()).get(PlayerViewModel::class.java)
        viewModel.selectedPlayer.observe(viewLifecycleOwner,
            Observer<String>{
                viewModel.getPlayerDetails()?.let { it -> displayDetails(it) };
            })
    }
```

`注意提醒` :

因為這兩個 Fragment 都是 host 在 MainActivity 上，所以摟~ viewModel 要向 MainActivity 取的，故`val viewModel = ViewModelProvider(requireActivity()).get(PlayerViewModel::class.java)`；是使用 requireActivity()。

後記:

以上程式都有可能是錯，而且保證絕對不是最好的寫法，只是剛好完成我想表達的東西，但保證拉下來是可以跑的。

小弟我心中有個疑問，為什麼 view model 可以隨意地穿梭在 fragment 間
，堪稱是安卓界的康斯坦丁，感覺`本體不存在於遊走的對象間`，於是小弟圖法煉鋼的去檢查記憶體位置，如下圖，得知了，`原來他就是一塊記憶體位置，並且他不會受到要傳遞的fragment、Activity影響`。

<img src="https://github.com/a86485563/Fragments-Communication-With-ViewModel/blob/master/memory.png" alt="memoryImage" width="600"/>


# ref

- fragment:
  1. https://developer.android.com/guide/fragments#getting-started
  2. https://ithelp.ithome.com.tw/articles/10221199
- view model:

  1. https://developer.android.com/topic/libraries/architecture/viewmodel

- 整合實作:
  1. https://blog.mindorks.com/shared-viewmodel-in-android-shared-between-fragments
  2. https://www.zoftino.com/passing-data-between-android-fragments-using-viewmodel
