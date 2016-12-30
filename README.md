# AdvancedWhoisPlus-Reloaded
プレイヤー情報表示プラグイン

### 【機能】
プレイヤーに関する情報を表示します。
Essentialsの/whoisからプラグイン依存の情報を除き、
ちょっとした有用な情報を表示します。
AdminCmd等別管理プラグインを入れておりEssentialsが使えない人などにもおすすめです。
以前のものを改良しました。

### 【コマンド】
- /whoisps help ・・・ ヘルプを表示します。
- /whoisps <playerID>  ・・・ プレイヤー情報を表示します。
- /whoisps checkver ・・・ 更新がないか確認します。
- /whoisps reload ・・・ Configをリロードします。

### 【Permissions】
- advwhois.* ・・・ 下記権限を全て許可します。
- advwhois.whoisps ・・・ プレイヤー情報表示を許可します。
- advwhois.joinshow ・・・ プレイヤーがログインした時に簡易情報を表示します。
- advwhois.bypass ・・・ 上記権限を所持したプレイヤーに情報が表示されないようにします。
- advwhois.ipshow ・・・ 簡易情報にIPを表示します。
- advwhois.reload ・・・ リロードを許可します。
- advwhois.updateinfo ・・・ プラグインの更新情報が表示されるようにします。
- advwhois.showalert ・・・ プラグインからのアラートを見れるようにします。

### 【Config】
		# AdvancedWhoisPlus #

		EnableJoinMassage: true
		AdditionalWhoisInfo: true

		# プレイヤー接続時に表示される簡易Whoisのモード設定です。
		# 1でIPが、2でHostNameが、3で両方表示されます。
		SimplicityWhoisMode: 1

		# %Player%を任意の場所に入れることでプレイヤー名を入れることができます。
		# %JCountryC%を任意の場所に入れることで接続国コードを入れることができます。
		JoinMassage: "&e%Player% has joined this server! - from %JCountryC%"

		debug: false

		# ここから下の行は編集しないでください
		###################
		ConfigVersion: 6

##LICENSE
		LICENSED BY GNU Lesser General Public License
![GNU License Logo](https://www.gnu.org/graphics/lgplv3-147x51.png)