(ns culture.facts
  "Country-level regional-culture catalog for Paraguay (PRY) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `statute.facts` (ADR-2607141700; this repo has no `marketentry.facts`
  yet, see that namespace's docstring); city-level counterparts live in
  the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"PRY"
   [{:culture/id "pry.dish.chipa"
     :culture/name "Chipa"
     :culture/country "PRY"
     :culture/kind :dish
     :culture/summary "Small, baked, cheese-flavored roll, a popular snack and breakfast food in Paraguay, with origins tracing to the 18th century among the Guarani people of Asuncion."
     :culture/url "https://en.wikipedia.org/wiki/Chipa"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "pry.dish.sopa-paraguaya"
     :culture/name "Sopa paraguaya"
     :culture/country "PRY"
     :culture/kind :dish
     :culture/summary "Traditional food in Paraguayan cuisine similar to corn bread, spongy and soft; the name means \"Paraguayan soup\" in Spanish."
     :culture/url "https://en.wikipedia.org/wiki/Sopa_paraguaya"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "pry.dish.mbeju"
     :culture/name "Mbeju"
     :culture/country "PRY"
     :culture/kind :dish
     :culture/summary "A starch cake, sometimes made with farina or manioc flour, typical of Paraguay."
     :culture/url "https://en.wikipedia.org/wiki/Mbeju"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "pry.beverage.terere"
     :culture/name "Terere"
     :culture/name-local "Tereré"
     :culture/country "PRY"
     :culture/kind :beverage
     :culture/summary "An infusion of yerba mate of Guarani origin, prepared with cold water, plentiful ice and medicinal herbs in a large vessel; originated in Paraguay and consumed cold, primarily in warmer regions."
     :culture/url "https://en.wikipedia.org/wiki/Terer%C3%A9"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "pry.craft.nanduti"
     :culture/name "Nanduti"
     :culture/name-local "Ñandutí"
     :culture/country "PRY"
     :culture/kind :craft
     :culture/summary "Traditional Paraguayan lace whose name means \"spider web\" in Guarani, created through needle-lace handicraft using cotton or silk threads worked on stretched fabric with intricate radiating patterns."
     :culture/url "https://en.wikipedia.org/wiki/%C3%91andut%C3%AD"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "pry.craft.paraguayan-harp"
     :culture/name "Paraguayan harp"
     :culture/name-local "Arpa paraguaya"
     :culture/country "PRY"
     :culture/kind :craft
     :culture/summary "The national instrument of Paraguay, the result of the confluence of European and Guarani musical cultures."
     :culture/url "https://en.wikipedia.org/wiki/Paraguayan_harp"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "pry.heritage.ruins-of-jesus-de-tavarangue"
     :culture/name "Ruins of Jesus de Tavarangue"
     :culture/name-local "Ruinas de Jesús de Tavarangue"
     :culture/country "PRY"
     :culture/kind :heritage
     :culture/summary "Jesuit mission ruins in Paraguay; together with those of Trinidad, they were designated a UNESCO World Heritage Site in 1993."
     :culture/url "https://en.wikipedia.org/wiki/Ruins_of_Jes%C3%BAs_de_Tavarangue"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-pry culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "PRY"))
                 " PRY entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
