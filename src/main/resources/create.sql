create table resource(
  id serial primary key ,
  link text
);

create table tag(
  id serial primary key ,
  title text
);

create table resource_tag(
  tag_id integer references resource(id),
  resource_id integer references tag(id)
);

SELECT resource_id from tag join resource_tag on tag.id = resource_tag.resource_id where tag.title='1'
INTERSECT
SELECT resource_id from tag join resource_tag on tag.id = resource_tag.resource_id where tag.title='3'
INTERSECT
SELECT resource_id from tag join resource_tag on tag.id = resource_tag.resource_id where tag.title='42'
INTERSECT
SELECT resource_id from tag join resource_tag on tag.id = resource_tag.resource_id where tag.title='56';

------------------------------------------------------------

explain analyse select * from resource_tag where tag_id=100;

create index resource_tag_tag_id_ind on resource_tag(tag_id);

-- hash index искать на равенство
drop index resource_tag_tag_id_ind;

create table resource1(id serial primary key ,link text, tags int[] );

explain analyse select * from resource1 where array[1, 50, 34]<@tags;
CREATE INDEX resource1_tags_ind ON resource1 USING gin(tags);
drop index resource1_tags_ind;