(ns statute.facts
  "General-law compliance catalog for Paraguay (PRY). Like
  cloud-itonami-iso3166-ury/-cri/-pan/-ecu, this repo had no
  `marketentry.facts` implementation yet (blueprint-only) -- this is
  the FIRST code-bearing content in this repo, self-contained with its
  own deps.edn. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/-chl/-arg/-zaf/-col/-ury/-cri/-pan/-ecu's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Bolivia was attempted first this tick and abandoned entirely:
  economiayfinanzas.gob.bo hit a TLS certificate error,
  gacetaoficialdebolivia.gob.bo hit ECONNRESET (twice, on two
  different URLs including its bare root), asfi.gob.bo failed with no
  output, and silep.gob.bo failed DNS resolution entirely -- four
  different official Bolivian government domains, four different
  failure classes, all unusable. None was cited.

  Entry 1 (Ley N.º 1.183/85, Código Civil): title directly confirmed
  by reading the saved conatel.gov.py PDF's own cover page (a
  different official Paraguayan government agency mirroring the law).
  bacn.gov.py (the primary official repository, Biblioteca y Archivo
  Central del Congreso Nacional) returned HTTP 403 on every URL tried
  this tick. The enacted/publication dates (1985-12-18 promulgation,
  1985-12-23 publication) are WebSearch-corroborated across two
  independent citing sources rather than directly read from an
  official page, matching the same discipline used for Gothenburg's
  ordningsföreskrifter in the previous tick.

  Entry 2 (Ley N.º 7593/2025, Protección de Datos Personales): title
  AND dates directly confirmed via silpy.congreso.gov.py (Paraguay's
  official Sistema de Información Legislativa del Congreso).

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries."
  {"PRY"
   [{:statute/id "pry.codigo-civil-ley-1183-85"
     :statute/title "Código Civil (Ley N.º 1.183/85)"
     :statute/jurisdiction "PRY"
     :statute/kind :law
     :statute/law-number "Ley N.º 1.183/85"
     :statute/url "https://www.conatel.gov.py/conatel/wp-content/uploads/2019/10/ley-1183_1985-cdigo-civil-paraguayo.pdf"
     :statute/url-provenance :official-conatel-gov-py-mirror
     :statute/enacted-date "1985-12-18"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "pry.ley-7593-2025-proteccion-datos"
     :statute/title "Ley N.º 7593/2025 (De Protección de Datos Personales en la República del Paraguay)"
     :statute/jurisdiction "PRY"
     :statute/kind :law
     :statute/law-number "Ley N.º 7593/2025"
     :statute/url "https://silpy.congreso.gov.py/web/ley/146223"
     :statute/url-provenance :official-silpy-congreso-gov-py
     :statute/enacted-date "2025-11-27"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:data-protection :privacy}}]})

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
      :note (str "cloud-itonami-iso3166-pry statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "PRY")) " PRY statutes seeded with "
                 "official conatel.gov.py/silpy.congreso.gov.py citations. "
                 "Extend `statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
