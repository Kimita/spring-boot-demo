# spring-boot-demo

これは、Spring Boot フレームワークをつかったWebアプリケーションの Demo Projectです。  
書籍「ユースケース駆動開発実践ガイド」に記載の手法を模倣してクラス設計をし、Spring Boot フレームワークの提供するMVC環境上で動作するコードを示します。

なお、当Projectは次のような **「Webエンジニア人材を教育する場面において有用な教材として活用できること」** という目的も併せ持って作成されています。
- Webエンジニア職への就業を希望するWeb開発未経験者が言語の習得を終えて開発演習に取り組むような場合に、指導者側ですぐに利用できる教材となること。
- Webエンジニア職の初級者を抜けて開発物のスパゲティコード化が気になり始めているエンジニアの中でも *「オブジェクト指向分析を伴って設計すること」にスパゲティ化回避の活路を見出した* ような方へ、オブジェクト指向分析を伴ったアプリケーション設計の実践例を示すこと。
- 書籍「ユースケース駆動開発実践ガイド（ダグ・ローゼンバーグ、マット・ステファン共著）」を読んでみたけどICONIXの何が良いのかイマイチピンとこなかったような人へ、メリットを紹介できるような資料となること。
- Webや書籍等で様々提供されている「ドメインモデリングに基づいた参考実装」や「読者をDDD(ドメイン駆動設計)へ導くべく書かれた参考記事」をエンジニア教育の実践に用いる場合に、「MVCフレームワークによるWebアプリケーション」においてすぐに使えるテンプレートとなること。

---

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**

- [設計資料の概要](#%E8%A8%AD%E8%A8%88%E8%B3%87%E6%96%99%E3%81%AE%E6%A6%82%E8%A6%81)
- [システムおよび機能の実行手順](#%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E3%81%8A%E3%82%88%E3%81%B3%E6%A9%9F%E8%83%BD%E3%81%AE%E5%AE%9F%E8%A1%8C%E6%89%8B%E9%A0%86)
- [設計資料の補足情報](#%E8%A8%AD%E8%A8%88%E8%B3%87%E6%96%99%E3%81%AE%E8%A3%9C%E8%B6%B3%E6%83%85%E5%A0%B1)
  - [設計における２つのテーマ](#%E8%A8%AD%E8%A8%88%E3%81%AB%E3%81%8A%E3%81%91%E3%82%8B%EF%BC%92%E3%81%A4%E3%81%AE%E3%83%86%E3%83%BC%E3%83%9E)
    - [Theme-1: オブジェクト指向言語上のWeb系MVCフレームワークで共通して使えるアプリケーション設計資料を作る](#theme-1-%E3%82%AA%E3%83%96%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E6%8C%87%E5%90%91%E8%A8%80%E8%AA%9E%E4%B8%8A%E3%81%AEweb%E7%B3%BBmvc%E3%83%95%E3%83%AC%E3%83%BC%E3%83%A0%E3%83%AF%E3%83%BC%E3%82%AF%E3%81%A7%E5%85%B1%E9%80%9A%E3%81%97%E3%81%A6%E4%BD%BF%E3%81%88%E3%82%8B%E3%82%A2%E3%83%97%E3%83%AA%E3%82%B1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E8%A8%AD%E8%A8%88%E8%B3%87%E6%96%99%E3%82%92%E4%BD%9C%E3%82%8B)
    - [Theme-2: 「ユースケース駆動」および「テスト駆動」な開発を、フレームワークに依存しないところで実践する](#theme-2-%E3%83%A6%E3%83%BC%E3%82%B9%E3%82%B1%E3%83%BC%E3%82%B9%E9%A7%86%E5%8B%95%E3%81%8A%E3%82%88%E3%81%B3%E3%83%86%E3%82%B9%E3%83%88%E9%A7%86%E5%8B%95%E3%81%AA%E9%96%8B%E7%99%BA%E3%82%92%E3%83%95%E3%83%AC%E3%83%BC%E3%83%A0%E3%83%AF%E3%83%BC%E3%82%AF%E3%81%AB%E4%BE%9D%E5%AD%98%E3%81%97%E3%81%AA%E3%81%84%E3%81%A8%E3%81%93%E3%82%8D%E3%81%A7%E5%AE%9F%E8%B7%B5%E3%81%99%E3%82%8B)
  - [上記テーマがもたらすメリット](#%E4%B8%8A%E8%A8%98%E3%83%86%E3%83%BC%E3%83%9E%E3%81%8C%E3%82%82%E3%81%9F%E3%82%89%E3%81%99%E3%83%A1%E3%83%AA%E3%83%83%E3%83%88)
    - [Benefit-1: フレームワーク選定が確定する前でもビジネスロジックの実装やテストを進められる](#benefit-1-%E3%83%95%E3%83%AC%E3%83%BC%E3%83%A0%E3%83%AF%E3%83%BC%E3%82%AF%E9%81%B8%E5%AE%9A%E3%81%8C%E7%A2%BA%E5%AE%9A%E3%81%99%E3%82%8B%E5%89%8D%E3%81%A7%E3%82%82%E3%83%93%E3%82%B8%E3%83%8D%E3%82%B9%E3%83%AD%E3%82%B8%E3%83%83%E3%82%AF%E3%81%AE%E5%AE%9F%E8%A3%85%E3%82%84%E3%83%86%E3%82%B9%E3%83%88%E3%82%92%E9%80%B2%E3%82%81%E3%82%89%E3%82%8C%E3%82%8B)
    - [Benefit-2: 移植性がかなり高まる](#benefit-2-%E7%A7%BB%E6%A4%8D%E6%80%A7%E3%81%8C%E3%81%8B%E3%81%AA%E3%82%8A%E9%AB%98%E3%81%BE%E3%82%8B)
- [実装に関する補足情報](#%E5%AE%9F%E8%A3%85%E3%81%AB%E9%96%A2%E3%81%99%E3%82%8B%E8%A3%9C%E8%B6%B3%E6%83%85%E5%A0%B1)
  - [Supplement-1: LoadDatabaseクラスについて](#supplement-1-loaddatabase%E3%82%AF%E3%83%A9%E3%82%B9%E3%81%AB%E3%81%A4%E3%81%84%E3%81%A6)
  - [Supplement-2: 動作確認可能となっているユースケースにおける未実装事項](#supplement-2-%E5%8B%95%E4%BD%9C%E7%A2%BA%E8%AA%8D%E5%8F%AF%E8%83%BD%E3%81%A8%E3%81%AA%E3%81%A3%E3%81%A6%E3%81%84%E3%82%8B%E3%83%A6%E3%83%BC%E3%82%B9%E3%82%B1%E3%83%BC%E3%82%B9%E3%81%AB%E3%81%8A%E3%81%91%E3%82%8B%E6%9C%AA%E5%AE%9F%E8%A3%85%E4%BA%8B%E9%A0%85)
  - [Supplement-3: 動作確認可能なユースケースへ後で追加する予定の事項](#supplement-3-%E5%8B%95%E4%BD%9C%E7%A2%BA%E8%AA%8D%E5%8F%AF%E8%83%BD%E3%81%AA%E3%83%A6%E3%83%BC%E3%82%B9%E3%82%B1%E3%83%BC%E3%82%B9%E3%81%B8%E5%BE%8C%E3%81%A7%E8%BF%BD%E5%8A%A0%E3%81%99%E3%82%8B%E4%BA%88%E5%AE%9A%E3%81%AE%E4%BA%8B%E9%A0%85)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

---

# 設計資料の概要

```
※注記
2021年9月30日時点では、以下に示す設計資料で定義されたユースケースのうち一部のみが実装されています。
未実装のユースケースについては、折を見て実装を行います。
```

1. [docs/UserAccountManagement/Required.md](/docs/UserAccountManagement/Required.md) で、(仮想の)対象システムにおける一般ユーザアカウント管理機能に要求されている振る舞いをリストしています。(いわゆる「要求定義」を、ICONIXプロセスに反映させるためのリストとして分解したもの)
2. [docs/UserAccountManagement/01_メイン資料/一般ユーザアカウント管理機能_概念モデル図.png](docs/UserAccountManagement/01_メイン資料/一般ユーザアカウント管理機能_概念モデル図.png) で、対象の問題領域(ドメイン)をモデリングしています。
3. [docs/UserAccountManagement/02_補助資料/補助資料.1_一般ユーザアカウントのライフサイクル.png](docs/UserAccountManagement/02_補助資料/補助資料.1_一般ユーザアカウントのライフサイクル.png) で、このアカウント管理機能が組み込まれるシステム内での「一般ユーザ」のライフサイクルを示しています。
4. [docs/UserAccountManagement/01_メイン資料/システム管理操作/システム管理操作UseCase図.png](docs/UserAccountManagement/01_メイン資料/システム管理操作/システム管理操作UseCase図.png) で、このアカウント管理機能におけるシステム管理者向けのユースケースを図示しています。
5. [docs/UserAccountManagement/01_メイン資料/一般ユーザ操作/一般ユーザ操作UseCase図.png](docs/UserAccountManagement/01_メイン資料/一般ユーザ操作/一般ユーザ操作UseCase図.png) で、このアカウント管理機能における一般ユーザ向けのユースケースを図示しています。
6. [docs/UserAccountManagement/01_メイン資料/一般ユーザ操作/アカウント作成の申請をする/](docs/UserAccountManagement/01_メイン資料/一般ユーザ操作/アカウント作成の申請をする/) ディレクトリには、ユースケース「アカウント作成の申請をする」に関する次の設計資料が収められています。
    1. ユースケース記述
    2. ロバストネス図
    3. シーケンス図
    4. クラス図
    5. GUIモック
7. [docs/UserAccountManagement/01_メイン資料/一般ユーザ操作/システムへログインする/](docs/UserAccountManagement/01_メイン資料/一般ユーザ操作/システムへログインする/) ディレクトリには、ユースケース「システムへログインする」に関する次の設計資料が収められています。
    1. ユースケース記述
    2. ロバストネス図
    3. シーケンス図
    4. クラス図
    5. GUIモック
8. [docs/UserAccountManagement/01_メイン資料/システム管理操作/システム管理コンソールへログインする/](docs/UserAccountManagement/01_メイン資料/システム管理操作/システム管理コンソールへログインする/) ディレクトリには、ユースケース「システム管理コンソールへログインする」に関する次の設計資料が収められています。
    1. ユースケース記述
    2. ロバストネス図
    3. シーケンス図
    4. クラス図
9. [docs/UserAccountManagement/03_pkg構成/PkgStructure.png](docs/UserAccountManagement/03_pkg構成/PkgStructure.png) で、各種ユースケースのクラス図で示された各種クラスがどのようなパッケージ構成(=ディレクトリ構成)で配置されるのかを図示しています。


# システムおよび機能の実行手順

1. git でローカルPC環境へ clone する。
2. 前項のローカルgitリポジトリで demo ブランチを checkout する。
3. 最新の Eclipse(Full Edition)環境 もしくは Spring Tool Suite環境 へ、 前項で checkout したプロジェクトをインポートする。
4. src/test/java に対して、JUnitテストを実行する。
5. src/main/java/page/clapandwhistle/demo/spring/SpringDemoApplication.java を Spring Boot アプリケーションとして起動する。
6. 以下の各種操作を行う（**URLはブラウザのアドレスバーへ直接入力してください**）
    1. http://localhost:8080/
        1. HomeController:index の表示がされる
        2. 「Login」リンク、もしくは「Myデスク」リンクをクリックする 
    2. http://localhost:8080/user-account/login/input
        1. ログイン画面が表示される
        2. Email「hoge01@example.local」、password「hoge01TEST」でログインを行い、MyWorkController:index の表示がされる
        3. 「ログアウト」ボタンをクリックする
    3. http://localhost:8080/user-account/login/input
        1. 前項とは異なる任意のメールアドレスでログインを行い、Eclipse(等のIDEが備える)コンソールに「入力されたメールアドレスを使用するユーザはいません」のエラーメッセージが表示される（今のところログイン画面へエラーメッセージ表示する実装はしていません）
    4. http://localhost:8080/user-account/new
        1. アカウント作成の申請画面が表示される
        2. Email「hoge02@example.local」、password「hoge02TEST」で「新規アカウントを作成する」ボタンのクリックを行い、HomeController:index の表示がされる
    5. http://localhost:8080/user-account/login/input
        1. ログイン画面が表示される
        2. Email「hoge02@example.local」、password「hoge02TEST」でログインを行い、MyWorkController:index の表示がされる
        3. 「ログアウト」ボタンをクリックする
    6. http://localhost:8080/admin-account/login/input
        1. 管理者ログイン画面が表示される
        2. Email「adm01@example.local」、password「hoge01TEST」でログインを行い、SystemConsoleController:index の表示がされる
        3. 「H2 Database」リンクより、h2-consoleへのログイン画面が表示される
        4. JDBC URL「jdbc:h2:mem:testdb」、User Name「sa」、Passwordは未入力、の状態で「Connect」ボタンをクリックしてh2-consoleの操作画面が表示される
    7. http://localhost:8080/admin/sys-console/index
       1. SystemConsoleController:index の表示がされる
       2. 「ログアウト」ボタンをクリックし、システム管理ユーザをログアウトする
       3. HomeController:index の表示がされる

```
※実は上記の ⅱ. と ⅴ. の結果は、実は要件定義・設計の考慮漏れを含んでいます。
「申請中」のステータスであるにも関わらずログインできてしまっているのは、当初の想定とは違った挙動であり、
後ほど修正するつもりです。(要件を「申請中でもログインはでき、別の制限がある」と変えて修正を回避するのもありかも?)
```
   

---

---


# 設計資料の補足情報

## 設計における２つのテーマ

※「テーマ」は「方針」と言い換えても良い。

### Theme-1: オブジェクト指向言語上のWeb系MVCフレームワークで共通して使えるアプリケーション設計資料を作る

書籍「 [ユースケース駆動開発実践ガイド ](https://www.shoeisha.co.jp/book/detail/9784798114453) 」で紹介されている **ICONIXプロセス** に則って分析・設計を行っています。  
ユースケース記述ごとにロバストネス分析を行い、そこからシーケンス図を描きつつ並行してクラス図を生成し、そのクラス図が示した構成を、言語やフレームワークをまたいで可能な限り共通に使います。

### Theme-2: 「ユースケース駆動」および「テスト駆動」な開発を、フレームワークに依存しないところで実践する

DDDで言うところの [オニオンアーキテクチャ](https://qiita.com/little_hand_s/items/ebb4284afeea0e8cc752#%E3%82%AA%E3%83%8B%E3%82%AA%E3%83%B3%E3%82%A2%E3%83%BC%E3%82%AD%E3%83%86%E3%82%AF%E3%83%81%E3%83%A3) が示す考え方をいくつか参考にして取り入れています。  
それにより、フレームワーク(UI層やInfra層やTest層)が何と差し替わろうともビジネスロジックが影響されない実装を行えます。

## 上記テーマがもたらすメリット

### Benefit-1: フレームワーク選定が確定する前でもビジネスロジックの実装やテストを進められる

- ユースケースで表現されるビジネスロジックは、Java や PHP や Python といった各言語のピュアな言語機能のみによってテスト駆動で実装します。
- そのため、プロダクトに使用するフレームワークやサードパーティ製ライブラリの選定期限に猶予がもたらされます。
- このことは、特に社内の人材育成プログラムにおいては次のようなメリットを生むでしょう。
    - フレームワークの実行環境がなんらかの事情で動かない場合でも、実装の話ができる。
    - 使用するフレームワークを(比較等のため)実験的に差し替えることの難易度(ハードル)が下がる。
    - 受講者が学習を希望する言語やフレームワークに応じて講師が模範解答などを用意する際に、講師が対象フレームワークに不慣れでも把握を焦らずに済む。

### Benefit-2: 移植性がかなり高まる

- **ビジネスロジックを** ユースケース層以降へ凝集するため、言語が同じならそれらは **まさにそのまま** 、また異なる言語でもクラス構成や属するメソッドの設計上のシグネチャ等はそのまま、 **移植できます** 。
    - なお、システム全体の移植に際して書き直しが必要になるのは次の事項です。
        - テストの記述
        - データソースから集約オブジェクトへのマッピング
        - プレゼンテーション層への値のマッピング
    - とは言えこれらも、しっかりビジネスロジックをユースケース層以降へ凝集できていれば、それぞれのコーディングはとてもシンプルな作業になります。
- **例えば、現状のJava向け実装と同じクラス構成でPHP向けの実装も実現できます**



# 実装に関する補足情報

## Supplement-1: LoadDatabaseクラスについて

- デモンストレーション用に初期データを登録する為のコードを書いてあります。
- maven のプロファイル機能によりこの初期データ登録処理が走る条件を切り替えらられるようにしてあります。

## Supplement-2: 動作確認可能となっているユースケースにおける未実装事項

-  *ユースケース「アカウント作成の申請をする」*
    - `プレゼンテーション層`: **「氏名」「生年月日」の入力欄**
    - `プレゼンテーション層`: **エラーメッセージの表示**

※上記で「プレゼンテーション層」の記述が指すのはMVCフレームワークの V(View) と C(Controller) のことです。

## Supplement-3: 動作確認可能なユースケースへ後で追加する予定の事項

- アカウント作成申請時のメール送信
- アカウント作成申請時のemail文字列バリデーション
